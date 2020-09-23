package ink.chevro.dto.blogger;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:05 2020-07-24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloggersInfoDTO {

    @ApiModelProperty(value = "笔名",name = "name")
    private String name;

    @ApiModelProperty(value = "头像",name = "penName")
    private String avatar;

    @ApiModelProperty(value = "个人介绍",name = "introduction")
    private String introduction;

    @ApiModelProperty(value = "邮箱",name = "email")
    private String email;

    @ApiModelProperty(value = "令牌",name = "token")
    private String token;

    @ApiModelProperty(value = "角色",name = "roles")
    private List<String> roles;
}
