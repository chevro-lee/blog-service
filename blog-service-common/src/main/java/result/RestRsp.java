package result;

import exception.BusinessException;
import exception.NotFoundException;
import exception.RpcException;
import utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  14:10 2019-08-11
 **/
public class RestRsp<T> {

    /**
     * 登录成功
     * @param data
     * @return
     */
    public RestResult<T> loginSuccess(T data) {
        return new RestRspBuilder<T>().success(CallResult.CALL_SUCCESS.value())
                .code(RspCode.LOGIN_SUCCESS.code())
                .msg(RspCode.LOGIN_SUCCESS.msg())
                .data(data)
                .exception(null)
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }

    /**
     *  登录失败
     * @param e
     * @return
     */
    public RestResult<T> loginFailed(Exception e){
        return new RestRspBuilder<T>().success(CallResult.CALL_FAILED.value())
                .code(RspCode.LOGIN_FAILED.code())
                .msg(RspCode.LOGIN_FAILED.msg())
                .data(null)
                .exception(e.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }

    /**
     * 请求成功
     * @param data
     * @return
     */
    public RestResult<T> success(T data) {
        return new RestRspBuilder<T>().success(CallResult.CALL_SUCCESS.value())
                .code(RspCode.SUCCESS.code())
                .msg(RspCode.SUCCESS.msg())
                .data(data)
                .exception(null)
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }

    /**
     * 系统内部出错
     * @param e
     * @return
     */
    public RestResult<T> sysError(Exception e){
        return new RestRspBuilder<T>().success(CallResult.CALL_FAILED.value())
                .code(RspCode.SYSTEM_ERROR.code())
                .msg(RspCode.SYSTEM_ERROR.msg())
                .data(null)
                .exception(e.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }

    /**
     * 外部服务调用异常
     * @param e
     * @return
     */
    public RestResult<T> rpcError(Exception e){
        return new RestRspBuilder<T>().success(CallResult.CALL_FAILED.value())
                .code(RspCode.RPC_ERROR.code())
                .msg(RspCode.RPC_ERROR.msg())
                .data(null)
                .exception(e.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    public RestResult<T> businessError(Exception e){
        return new RestRspBuilder<T>().success(CallResult.CALL_FAILED.value())
                .code(RspCode.LOGIC_ERROR.code())
                .msg(RspCode.LOGIC_ERROR.msg())
                .data(null)
                .exception(e.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }

    /**
     * 未找到
     * @param e
     * @return
     */
    public RestResult<T> notFound(Exception e){
        return new RestRspBuilder<T>().success(CallResult.CALL_FAILED.value())
                .code(RspCode.NOT_FOUND.code())
                .msg(RspCode.NOT_FOUND.msg())
                .data(null)
                .exception(e.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }

    /**
     * TOKEN无效
     * @param e
     * @return
     */
    public RestResult<T> tokenInvalid(Exception e) {
        return new RestRspBuilder<T>().success(CallResult.CALL_FAILED.value())
                .code(RspCode.FORBIDDEN.code())
                .msg(RspCode.FORBIDDEN.msg())
                .data(null)
                .exception(e.getMessage())
                .timestamp(DateUtils.dateToStamp(new Date()))
                .build();
    }
}
