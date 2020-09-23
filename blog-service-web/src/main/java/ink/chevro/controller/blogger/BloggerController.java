package ink.chevro.controller.blogger;

import ink.chevro.admin.entity.blogger.Blogger;
import ink.chevro.admin.service.blogger.IBloggerService;
import ink.chevro.dto.blogger.BloggersInfoDTO;
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
import java.util.Collections;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  16:57 2020-07-24
 **/
@Api(value = "BloggerController",tags = "用户管理")
@RestController
@RequestMapping("/api/v1")
public class BloggerController {

    @Resource
    private IBloggerService bloggerService;

    @ApiOperation("获取博主信息")
    @PostMapping("/users/info")
    public RestResult<BloggersInfoDTO> getBloggersInfo(@RequestHeader("Authorization") String authorization) {
        RestRsp<BloggersInfoDTO> restRsp = new RestRsp<>();
        authorization = authorization.replace(JwtTokenUtils.TOKEN_PREFIX,"");
        String username = JwtTokenUtils.getUsername(authorization);
        String role = JwtTokenUtils.getUserRole(authorization);
        Blogger blogger = bloggerService.getBloggerInfo(authorization, username);
        BloggersInfoDTO bloggersInfoDTO = new BloggersInfoDTO();
        if (blogger != null) {
            bloggersInfoDTO.setName(blogger.getUsername());
            bloggersInfoDTO.setAvatar(blogger.getAvatar());
            bloggersInfoDTO.setEmail(blogger.getEmail());
            bloggersInfoDTO.setIntroduction(blogger.getIntroduction());
            bloggersInfoDTO.setToken(authorization);
            bloggersInfoDTO.setRoles(Collections.singletonList(role));
        }
        return restRsp.success(bloggersInfoDTO);
    }
}
