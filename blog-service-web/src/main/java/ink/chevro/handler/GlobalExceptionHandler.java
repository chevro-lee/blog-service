package ink.chevro.handler;

import exception.BusinessException;
import exception.NotFoundException;
import exception.RpcException;
import exception.TokenInvalidException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import result.RestResult;
import result.RestRsp;

/**
 * Author: Chevro.Lee <br>
 * Description: 全局异常处理类
 * Date: Create in  17:05 2019-08-09
 **/
@RestController
@ControllerAdvice
public class GlobalExceptionHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResult<T>> handleUncaughtExceptions(HttpServletRequest request, Exception e) {
        // 发生异常统一处理返回结果
        RestResult<T> restResult;
        if (e instanceof BusinessException) {
            restResult = new RestRsp<T>().businessError(e);
        } else if (e instanceof RpcException) {
            restResult = new RestRsp<T>().rpcError(e);
        } else if (e instanceof NotFoundException) {
            restResult = new RestRsp<T>().notFound(e);
        } else if (e instanceof TokenInvalidException) {
            restResult = new RestRsp<T>().tokenInvalid(e);
        } else {
            restResult = new RestRsp<T>().sysError(e);
        }
        logger.error("---Exception Handler--- Type = {}; Host = {}; invokes url = {}; ",
                e.getClass().getSimpleName(),
                request.getRemoteHost(),
                request.getRequestURL(), e);
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }
}
