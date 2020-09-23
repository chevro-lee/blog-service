package ink.chevro.service;

import ink.chevro.admin.entity.blogger.Blogger;
import ink.chevro.admin.entity.system.SysRole;
import ink.chevro.admin.service.blogger.impl.BloggerService;
import ink.chevro.admin.service.role.impl.RoleService;
import ink.chevro.entity.JwtUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  10:51 2020-07-09
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private BloggerService bloggerService;

    @Resource
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Blogger blogger = bloggerService.findByName(username);
        List<SysRole> roles = roleService.selectByBloggerId(blogger.getId());
        // 一个账号一个角色
        String role = roles.get(0).getRoleKey();
        return new JwtUser(blogger.getId(), blogger.getUsername(), blogger.getPassword(), role);
    }
}
