package result;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  1:38 2019-08-11
 **/
public class RestRspBuilder<T> {

    private boolean success;
    private String code;
    private String msg;
    private T data;
    private String exception;
    private String timestamp;

    public RestRspBuilder<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public RestRspBuilder<T> code(String code) {
        this.code = code;
        return this;
    }

    public RestRspBuilder<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public RestRspBuilder<T> data(T data) {
        this.data = data;
        return this;
    }

    public RestRspBuilder<T> exception(String exception) {
        this.exception = exception;
        return this;
    }

    public RestRspBuilder<T> timestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public RestResult<T> build() {
        return new RestResult<>(success, code, msg, data, exception, timestamp);
    }
}
