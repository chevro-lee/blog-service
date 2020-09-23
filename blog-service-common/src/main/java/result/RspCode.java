package result;

/**
 * Author: Chevro.Lee <br>
 * Description: Rest API接口响应码
 * Date: Create in  17:06 2019-08-09
 **/
public enum RspCode {

    LOGIN_SUCCESS("201","登录成功"),
    LOGIN_FAILED("503", "登录失败"),
    SUCCESS("200","成功"),
    FORBIDDEN("403","禁止访问"),
    NOT_FOUND("404","未找到"),
    SYSTEM_ERROR("500","服务器内部错误"),
    LOGIC_ERROR("501","服务执行异常"),
    RPC_ERROR("502","外部服务调用异常")
    ;

    private final String code;

    private final String msg;

    RspCode(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}