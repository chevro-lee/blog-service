package exception;

import base.BaseException;

/**
 * Author: Chevro.Lee <br>
 * Description: Redis、DB、MQ等第三方系统连接异常
 * Date: Create in  10:33 2019-08-12
 **/
public class RpcException extends BaseException {

    public RpcException(String code, String msg) {
        super(code, msg);
    }
}
