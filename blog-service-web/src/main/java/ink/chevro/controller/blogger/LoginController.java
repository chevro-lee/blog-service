package ink.chevro.controller.blogger;



import ch.qos.logback.core.joran.util.beans.BeanUtil;
import ink.chevro.admin.entity.blogger.Blogger;
import ink.chevro.admin.service.blogger.impl.BloggerService;
import ink.chevro.dto.blogger.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import result.RestResult;
import result.RestRsp;

import javax.annotation.Resource;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:42 2020-07-10
 **/
@Api(value = "LoginController",tags = "登录注册")
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Resource
    private BloggerService bloggerService;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public RestResult<RegisterDTO> register(@RequestBody RegisterDTO registerDTO) {
        RestRsp<RegisterDTO> restRsp = new RestRsp<>();
        Blogger blogger = new Blogger();
        BeanUtils.copyProperties(registerDTO, blogger);
        blogger.setUsername(registerDTO.getPenName());
        Blogger insertBlogger = bloggerService.register(blogger);
        BeanUtils.copyProperties(insertBlogger, registerDTO);
        return restRsp.success(registerDTO);
    }
}
