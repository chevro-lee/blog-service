package ink.chevro.controller.system;

import ink.chevro.admin.service.system.ISysMenuService;
import ink.chevro.dto.blogger.BloggersInfoDTO;
import ink.chevro.dto.system.MenuDTO;
import ink.chevro.jwt.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.RestResult;
import result.RestRsp;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:32 2020-09-03
 **/
@Api(value = "SysMenuController",tags = "菜单")
@RestController
@RequestMapping("/api/v1")
public class SysMenuController {

    @Resource
    private ISysMenuService ISysMenuService;

    @ApiOperation("获取菜单信息")
    @PostMapping("/menu/auth")
    public RestResult<List<MenuDTO>> getMenu(@RequestHeader("Authorization") String authorization) {
        RestRsp<List<MenuDTO>> restRsp = new RestRsp<>();
        authorization = authorization.replace(JwtTokenUtils.TOKEN_PREFIX,"");
        String userRole = JwtTokenUtils.getUserRole(authorization);
        List<MenuDTO> menuAuthList = ISysMenuService.getMenuAuth(userRole);
        return restRsp.success(menuAuthList);
    }
}
