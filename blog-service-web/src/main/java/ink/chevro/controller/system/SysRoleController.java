package ink.chevro.controller.system;

import ink.chevro.admin.entity.system.SysRole;
import ink.chevro.admin.service.system.ISysRoleService;
import ink.chevro.dto.system.RoleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.RestResult;
import result.RestRsp;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  2020/10/26 16:18
 **/
@Api(value = "SysRoleController",tags = "角色")
@RestController
@RequestMapping("/api/v1")
public class SysRoleController {

    @Resource
    private ISysRoleService roleService;

    @ApiOperation("获取菜单信息")
    @GetMapping("/roles")
    public RestResult<List<RoleDTO>> getRoles() {
        RestRsp<List<RoleDTO>> restResult = new RestRsp<>();
        List<SysRole> sysRoles = roleService.selectRoles();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setKey(sysRole.getRoleKey());
            roleDTO.setName(sysRole.getRoleName());
            roleDTO.setDescription(sysRole.getRoleComment());
            roleDTOS.add(roleDTO);
        }
        return restResult.success(roleDTOS);
    }
}
