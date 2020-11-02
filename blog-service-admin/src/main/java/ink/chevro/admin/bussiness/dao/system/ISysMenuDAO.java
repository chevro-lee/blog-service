package ink.chevro.admin.bussiness.dao.system;

import ink.chevro.dto.system.MenuDTO;

import java.util.List;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  10:53 2020-08-21
 **/
public interface ISysMenuDAO {

    /**
     * 获取所有的菜单
     * @return
     */
    List<MenuDTO> getMenusAll(String role);

    /**
     * 获取被授予的角色权限清单
     * @param role
     * @return
     */
    List<MenuDTO> getMenusAuth(String role);


    /**
     * 获取被授予的角色权限清单与角色的映射关系
     * @param roleList
     * @return
     */
    Map<String, List<MenuDTO>> mapMenusAuth(List<String> roleList);
}
