package ink.chevro.handler;

import ink.chevro.jwt.JwtTokenUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ink.chevro.redis.impl.RedisStringClient;
import result.CallResult;
import result.RestResult;
import result.RestRspBuilder;
import result.RspCode;
import utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:19 2020-07-28
 **/
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    private RedisStringClient redisStringClient;

    public UserLogoutSuccessHandler(RedisStringClient redisStringClient) {
        this.redisStringClient = redisStringClient;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        RestResult<String> restResult = new RestRspBuilder<String>().success(CallResult.CALL_SUCCESS.value())
                .code(RspCode.SUCCESS.code())
                .msg(RspCode.SUCCESS.msg())
                .exception(null)
                .data(null)
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
        response.setContentType("application/json");
        // 删除redis中的token
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        redisStringClient.del(token);
        // 清除SecurityContextHolder
        SecurityContextHolder.clearContext();
        String jsonResult = new ObjectMapper().writeValueAsString(restResult);
        response.getOutputStream().write(jsonResult.getBytes());
        response.flushBuffer();
    }
}
