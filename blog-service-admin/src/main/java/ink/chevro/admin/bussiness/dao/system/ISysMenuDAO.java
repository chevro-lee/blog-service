package ink.chevro.admin.bussiness.dao.system;

import ink.chevro.dto.system.MenuDTO;

import java.util.List;

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
}
