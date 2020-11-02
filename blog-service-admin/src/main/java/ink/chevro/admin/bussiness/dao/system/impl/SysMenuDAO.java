package ink.chevro.admin.bussiness.dao.system.impl;

import ink.chevro.admin.bussiness.dao.system.ISysMenuDAO;
import ink.chevro.admin.entity.system.SysMenu;
import ink.chevro.admin.entity.system.SysRoleMenu;
import ink.chevro.admin.entity.system.SysRoleMenuExample;
import ink.chevro.admin.enums.system.MenuSwitchEnum;
import ink.chevro.admin.mybatis.mapper.system.SysMenuMapper;
import ink.chevro.admin.mybatis.mapper.system.SysRoleMenuMapper;
import ink.chevro.dto.system.MenuDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:55 2020-09-02
 **/
@Component
public class SysMenuDAO implements ISysMenuDAO {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<MenuDTO> getMenusAll(String role) {
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(null);
        if (CollectionUtils.isEmpty(sysMenus)) {
            return null;
        }
        SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
        sysRoleMenuExample.createCriteria().andRoleKeyEqualTo(role);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectByExample(sysRoleMenuExample);
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuCode(sysMenu.getMenuCode());
            menuDTO.setAlwaysShow(MenuSwitchEnum.getValue(sysMenu.getAlwaysShow()));
            menuDTO.setComponent(sysMenu.getComponent());
            menuDTO.setHidden(MenuSwitchEnum.getValue(sysMenu.getHidden()));
            menuDTO.setIcon(sysMenu.getIcon());
            menuDTO.setLevel(sysMenu.getLevel());
            menuDTO.setName(sysMenu.getName());
            menuDTO.setPath(sysMenu.getPath());
            menuDTO.setRedirect(sysMenu.getRedirect());
            menuDTO.setSort(sysMenu.getSort());
            menuDTO.setTitle(sysMenu.getTitle());
            menuDTO.setParent(sysMenu.getParent());
            for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
                String menuCode = sysRoleMenu.getMenuCode();
                // 如果当前角色有此菜单权限
                if (menuCode.equals(menuDTO.getMenuCode())) {
                    // 角色设置
                    menuDTO.setRoles(Collections.singletonList(role));
                }
            }
            menuDTOList.add(menuDTO);
        }
        // 构造菜单路由数据结构
        menuDTOList = constructMenu(menuDTOList);
        return menuDTOList;
    }

    @Override
    public List<MenuDTO> getMenusAuth(String role) {
        List<SysMenu> menuAuthList = sysMenuMapper.selectMenuByRole(role);
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (SysMenu sysMenu : menuAuthList) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuCode(sysMenu.getMenuCode());
            menuDTO.setAlwaysShow(MenuSwitchEnum.getValue(sysMenu.getAlwaysShow()));
            menuDTO.setHidden(MenuSwitchEnum.getValue(sysMenu.getHidden()));
            menuDTO.setComponent(sysMenu.getComponent());
            menuDTO.setPath(sysMenu.getPath());
            menuDTO.setName(sysMenu.getName());
            menuDTO.setIcon(sysMenu.getIcon());
            menuDTO.setLevel(sysMenu.getLevel());
            menuDTO.setRedirect(sysMenu.getRedirect());
            menuDTO.setSort(sysMenu.getSort());
            menuDTO.setTitle(sysMenu.getTitle());
            menuDTO.setParent(sysMenu.getParent());
            menuDTOList.add(menuDTO);
        }
        // 构造菜单路由数据结构
        menuDTOList = constructMenu(menuDTOList);
        return menuDTOList;
    }

    @Override
    public Map<String, List<MenuDTO>> mapMenusAuth(List<String> roleList) {
        Map<String, List<MenuDTO>> map = new HashMap<>();
        for (String role : roleList) {
            List<MenuDTO> menusAuth = getMenusAuth(role);
            map.put(role, menusAuth);
        }
        return map;
    }

    protected List<MenuDTO> constructMenu(List<MenuDTO> menuDTOList) {
        List<MenuDTO> constructMenuList = new ArrayList<>();
        for (MenuDTO menuDTO : menuDTOList) {
            String parent = menuDTO.getParent();
            // 从一级菜单开始
            if (!StringUtils.isEmpty(parent)) {
                continue;
            }
            List<MenuDTO> childMenuDTOList = findChild(menuDTO, menuDTOList);
            menuDTO.setChildren(childMenuDTOList);
            constructMenuList.add(menuDTO);
        }
        return constructMenuList;
    }

    // 递归查找当前菜单的子菜单
    protected List<MenuDTO> findChild(MenuDTO menuDTO, List<MenuDTO> menuDTOList) {
        String menuCode = menuDTO.getMenuCode();
        List<MenuDTO> menuDTOs = new ArrayList<>();
        for (MenuDTO m : menuDTOList) {
            String parent = m.getParent();
            if (menuCode.equals(parent)) {
                menuDTOs.add(m);
                findChild(m, menuDTOList);
            }
            menuDTO.setChildren(menuDTOs);
        }
        return menuDTOs;
    }
}
