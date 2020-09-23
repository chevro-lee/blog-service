package exception;

import base.BaseException;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  14:59 2020-07-10
 **/
public class TokenInvalidException extends BaseException {

    public TokenInvalidException(String code, String msg) {
        super(code, msg);
    }
}
