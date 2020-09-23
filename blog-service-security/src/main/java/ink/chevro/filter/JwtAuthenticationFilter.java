package ink.chevro.filter;

import ink.chevro.entity.JwtUser;
import ink.chevro.entity.LoginUser;
import ink.chevro.jwt.JwtTokenUtils;
import ink.chevro.redis.impl.RedisStringClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import result.CallResult;
import result.RestResult;
import result.RestRspBuilder;
import result.RspCode;
import utils.DateUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author: Chevro.Lee <br>
 * Description: 认证
 * Date: Create in  16:39 2020-07-09
 **/
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 认证管理器
     */
    private AuthenticationManager authenticationManager;

    private RedisStringClient redisStringClient;

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RedisStringClient redisStringClient) {
        this.authenticationManager = authenticationManager;
        this.redisStringClient = redisStringClient;
        super.setFilterProcessesUrl("/api/v1/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 从输入流获取登录信息
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            rememberMe.set(loginUser.getRememberMe() == null ? 0 : loginUser.getRememberMe());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        boolean isRememberMe = rememberMe.get() == 1;
        String token = JwtTokenUtils.createAccessToken(jwtUser, isRememberMe);
        // 保存token至redis
        String jwtUserJson = new ObjectMapper().writeValueAsString(jwtUser);
        redisStringClient.set(JwtTokenUtils.TOKEN_PREFIX + token, jwtUserJson, JwtTokenUtils.EXPIRATION, TimeUnit.SECONDS);
        // 返回创建成功的token
        RestResult<String> restResult = new RestRspBuilder<String>().success(CallResult.CALL_SUCCESS.value())
                .code(RspCode.LOGIN_SUCCESS.code())
                .msg(RspCode.LOGIN_SUCCESS.msg())
                .data(JwtTokenUtils.TOKEN_PREFIX + token)
                .exception(null)
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
        response.setContentType("application/json");
        String jsonResult = new ObjectMapper().writeValueAsString(restResult);
        response.getOutputStream().write(jsonResult.getBytes());
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        RestResult<Void> restResult = new RestRspBuilder<Void>().success(CallResult.CALL_FAILED.value())
                .code(RspCode.LOGIN_FAILED.code())
                .msg(RspCode.LOGIN_FAILED.msg())
                .data(null)
                .exception(failed.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
        String restResultJson = new ObjectMapper().writeValueAsString(restResult);
        response.setContentType("application/json");
        response.getOutputStream().write(restResultJson.getBytes());
        response.flushBuffer();
    }
}
