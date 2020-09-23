package ink.chevro.admin.service.role.impl;

import base.BaseService;
import ink.chevro.admin.entity.blogger.BloggerRole;
import ink.chevro.admin.entity.blogger.BloggerRoleExample;
import ink.chevro.admin.entity.system.SysRole;
import ink.chevro.admin.entity.system.SysRoleExample;
import ink.chevro.admin.mybatis.mapper.blogger.BloggerRoleMapper;
import ink.chevro.admin.mybatis.mapper.system.SysRoleMapper;
import ink.chevro.admin.service.role.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:16 2020-07-21
 **/
@Service
public class RoleService extends BaseService implements IRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private BloggerRoleMapper bloggerRoleMapper;

    @Override
    public List<SysRole> selectByBloggerId(String id) {
        BloggerRoleExample bloggerRoleExample = new BloggerRoleExample();
        bloggerRoleExample.createCriteria().andBloggerIdEqualTo(id);
        List<BloggerRole> bloggerRoles = bloggerRoleMapper.selectByExample(bloggerRoleExample);
        List<Integer> roleIdList = new ArrayList<>();
        for (BloggerRole bloggerRole : bloggerRoles) {
            roleIdList.add(bloggerRole.getRoleId());
        }
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andIdIn(roleIdList);
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public Map<String, String> selectRole() {
        List<SysRole> roleList = roleMapper.selectAll();
        Map<String, String> roleMap = new HashMap<>();
        for (SysRole role : roleList) {
            roleMap.put(role.getRoleKey(), role.getRoleName());
        }
        return roleMap;
    }

    @Override
    public List<SysRole> selectRoles() {
        return roleMapper.selectByExample(null);
    }
}
