package ink.chevro.controller.user;

import ink.chevro.admin.entity.user.User;
import ink.chevro.admin.service.user.impl.UserService;
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
 * Date: Create in  19:22 2019-11-11
 **/
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public RestResult<User> register(@RequestBody User user) {
        RestRsp<User> restRsp = new RestRsp<>();
        userService.register(user);
        return restRsp.success(user);
    }
}
