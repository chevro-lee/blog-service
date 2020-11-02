package ink.chevro.admin.service.system.impl;

import base.BaseService;
import ink.chevro.admin.bussiness.dao.system.ISysMenuDAO;
import ink.chevro.admin.entity.blogger.BloggerRole;
import ink.chevro.admin.entity.blogger.BloggerRoleExample;
import ink.chevro.admin.entity.system.SysMenu;
import ink.chevro.admin.entity.system.SysRole;
import ink.chevro.admin.entity.system.SysRoleExample;
import ink.chevro.admin.entity.system.SysRoleMenu;
import ink.chevro.admin.mybatis.mapper.blogger.BloggerRoleMapper;
import ink.chevro.admin.mybatis.mapper.system.SysMenuMapper;
import ink.chevro.admin.mybatis.mapper.system.SysRoleMapper;
import ink.chevro.admin.service.system.ISysRoleService;
import ink.chevro.dto.system.MenuDTO;
import ink.chevro.dto.system.RoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:16 2020-07-21
 **/
@Service
public class SysRoleService extends BaseService implements ISysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private BloggerRoleMapper bloggerRoleMapper;

    @Resource
    private ISysMenuDAO sysMenuDAO;

    @Resource
    private SysMenuMapper sysMenuMapper;

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
    public List<RoleDTO> selectRoles() {
        List<SysRole> sysRoles = roleMapper.selectByExample(null);
        List<String> roleList = sysRoles.stream().map(SysRole::getRoleKey).collect(Collectors.toList());
        Map<String, List<MenuDTO>> menusAuthMap = sysMenuDAO.mapMenusAuth(roleList);
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setKey(sysRole.getRoleKey());
            roleDTO.setName(sysRole.getRoleName());
            roleDTO.setDescription(sysRole.getRoleComment());
            roleDTO.setRoutes(menusAuthMap.get(sysRole.getRoleKey()));
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }

    @Override
    public int updateRoles(String roleKey, RoleDTO roleDTO) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        List<MenuDTO> routes = roleDTO.getRoutes();
        List<String> pathList = findPath(routes);
        List<SysMenu> sysMenus = sysMenuMapper.selectMenuByPaths(pathList);
        return 0;
    }

    private List<String> findPath(List<MenuDTO> routes) {
        if (CollectionUtils.isEmpty(routes)) {
            return null;
        }
        List<String> pathList = new ArrayList<>();
        for (MenuDTO menuDTO : routes) {
            if (!ObjectUtils.isEmpty(menuDTO.getChildren())) {
                findPath(menuDTO.getChildren());
            }
            pathList.add(menuDTO.getPath());
        }
        return pathList;
    }

}
