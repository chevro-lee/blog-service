package result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Auther: Chevro.Lee <br>
 * Description: Rest 统一接口返回格式类
 * Date: Create in  16:11 2019-08-09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Rest Api Result")
public class RestResult<T> implements Serializable {

    /**
     * 序列
     */
    private static final long serialVersionUID = 2111823492100739240L;

    @ApiModelProperty("调用结果（true，调用成功    false，调用失败）")
    private boolean success;

    @ApiModelProperty("Http状态码（200，调用成功    403，禁止访问    404，服务不存在    500，服务出错）")
    private String code;

    @ApiModelProperty("详细信息")
    private String msg;

    @ApiModelProperty("结果集")
    private T data;

    @ApiModelProperty("异常内容")
    private String exception;

    @ApiModelProperty("调用时间")
    private String timestamp;
}
