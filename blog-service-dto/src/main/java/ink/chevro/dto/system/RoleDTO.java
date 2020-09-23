package ink.chevro.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  14:28 2020-08-18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "角色")
public class RoleDTO {

    @ApiModelProperty(value = "角色键",name = "roleKey")
    private String roleKey;

    @ApiModelProperty(value = "角色名", name = "roleName")
    private String roleName;

    @ApiModelProperty(value = "描述", name = "description")
    private String description;
}
