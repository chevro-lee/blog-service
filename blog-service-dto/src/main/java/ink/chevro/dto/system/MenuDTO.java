package ink.chevro.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  16:37 2020-09-02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "菜单")
public class MenuDTO {

    @ApiModelProperty(value = "路由code",name = "menuCode")
    private String menuCode;

    @ApiModelProperty(value = "父节点",name = "parent")
    private String parent;

    @ApiModelProperty(value = "路由",name = "path")
    private String path;

    @ApiModelProperty(value = "组件",name = "component")
    private String component;

    @ApiModelProperty(value = "定向",name = "redirect")
    private String redirect;

    @ApiModelProperty(value = "菜单名",name = "name")
    private String name;

    @ApiModelProperty(value = "标题",name = "title")
    private String title;

    @ApiModelProperty(value = "图标",name = "icon")
    private String icon;

    @ApiModelProperty(value = "隐藏",name = "hidden")
    private Boolean hidden;

    @ApiModelProperty(value = "一直显示",name = "alwaysShow")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "子菜单",name = "children")
    private List<MenuDTO> children;

    @ApiModelProperty(value = "排序",name = "sort")
    private Integer sort;

    @ApiModelProperty(value = "等级",name = "level")
    private String level;

    @ApiModelProperty(value = "角色",name = "roles")
    private List<String> roles;
}
