package result;

/**
 * Author: Chevro.Lee <br>
 * Description: 调用结果枚举
 * Date: Create in  17:21 2019-08-09
 **/
public enum CallResult {

    CALL_SUCCESS(true),
    CALL_FAILED(false)
    ;

    private boolean success;

    CallResult(boolean success) {
        this.success = success;
    }

    public boolean value() {
        return this.success;
    }
}
