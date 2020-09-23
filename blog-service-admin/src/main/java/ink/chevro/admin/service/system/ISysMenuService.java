package ink.chevro.admin.service.system;

import ink.chevro.dto.system.MenuDTO;

import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:30 2020-09-03
 **/
public interface ISysMenuService {

    List<MenuDTO> getMenuAuth(String role);
}
