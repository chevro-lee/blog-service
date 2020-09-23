package ink.chevro.dto.blogger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:08 2020-07-21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户注册实体")
public class RegisterDTO {

    @ApiModelProperty(value = "笔名",name = "penName",required = true)
    private String penName;

    @ApiModelProperty(value = "密码",name = "password",required = true)
    private String password;

    @ApiModelProperty(value = "邮箱",name = "email",required = true)
    private String email;

    @ApiModelProperty(value = "手机",name = "phone")
    private String phone;
}
