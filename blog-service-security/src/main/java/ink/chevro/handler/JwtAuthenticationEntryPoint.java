package ink.chevro.handler;

import ink.chevro.jwt.JwtTokenUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import result.*;
import utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Author: Chevro.Lee <br>
 * Description: 没有携带TOKEN或者TOKEN无效的异常处理
 * Date: Create in  11:44 2020-07-29
 **/
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        RestResult<String> restResult = new RestRspBuilder<String>().success(CallResult.CALL_SUCCESS.value())
                .code(RspCode.FORBIDDEN.code())
                .msg(RspCode.FORBIDDEN.msg())
                .data(null)
                .exception(authException.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
        response.setContentType("application/json");
        String jsonResult = new ObjectMapper().writeValueAsString(restResult);
        response.getOutputStream().write(jsonResult.getBytes());
        response.flushBuffer();
    }
}
