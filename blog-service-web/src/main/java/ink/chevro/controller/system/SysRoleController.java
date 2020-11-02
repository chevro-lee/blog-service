package ink.chevro.controller.system;

import ink.chevro.admin.entity.system.SysRole;
import ink.chevro.admin.service.system.ISysRoleService;
import ink.chevro.dto.system.RoleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation("获取角色信息")
    @GetMapping("/roles")
    public RestResult<List<RoleDTO>> getRoles() {
        RestRsp<List<RoleDTO>> restResult = new RestRsp<>();
        List<RoleDTO> roleDTOList = roleService.selectRoles();
        return restResult.success(roleDTOList);
    }

    @ApiOperation("修改角色信息")
    @PutMapping("/roles/{role}")
    public RestResult<Void> updateRoles(@PathVariable("role") String role, @RequestBody RoleDTO roleDTO) {
        RestRsp<Void> restResult = new RestRsp<>();

        return restResult.success(null);
    }
}
