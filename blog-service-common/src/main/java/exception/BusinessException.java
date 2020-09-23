package exception;

import base.BaseException;

/**
 * Author: Chevro.Lee <br>
 * Description: 业务逻辑异常
 * Date: Create in  0:59 2019-08-11
 **/
public class BusinessException extends BaseException {

    public BusinessException(String code, String msg) {
        super(code, msg);
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String msg, Throwable e) {
        super(msg,e);
    }

}
