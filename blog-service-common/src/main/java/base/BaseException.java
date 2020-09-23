package base;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:24 2019-08-12
 **/
public abstract class BaseException extends RuntimeException {

    private String code;
    private String msg;

    public BaseException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(Throwable e){
        super(e);
    }

    public BaseException(String msg,Throwable e) {
        super(msg,e);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
