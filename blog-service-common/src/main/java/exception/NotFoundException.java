package exception;

import base.BaseException;

/**
 * Author: Chevro.Lee <br>
 * Description: 服务未找到
 * Date: Create in  11:25 2019-08-12
 **/
public class NotFoundException extends BaseException {

    public NotFoundException(String code, String msg) {
        super(code, msg);
    }
}
