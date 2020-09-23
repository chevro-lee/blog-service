package ink.chevro.handler;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import result.*;
import utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Author: Chevro.Lee <br>
 * Description: 没有权限时的异常处理
 * Date: Create in  11:55 2020-07-29
 **/
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        RestResult<String> restResult = new RestRspBuilder<String>().success(CallResult.CALL_SUCCESS.value())
                .data(null)
                .code(RspCode.FORBIDDEN.code())
                .msg(RspCode.FORBIDDEN.msg())
                .exception(accessDeniedException.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
        response.setContentType("application/json");
        String jsonResult = new ObjectMapper().writeValueAsString(restResult);
        response.getOutputStream().write(jsonResult.getBytes());
        response.flushBuffer();
    }
}
