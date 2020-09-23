package ink.chevro.admin.service.role;


import ink.chevro.admin.entity.system.SysRole;

import java.util.List;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:16 2020-07-21
 **/
public interface IRoleService {

    /**
     * 查询博主角色
     *
     * @param id
     * @return
     */
    List<SysRole> selectByBloggerId(String id);

    /**
     * 查询所有的角色
     *
     * @return
     */
    Map<String, String> selectRole();

    /**
     * 查询所有的角色
     *
     * @return
     */
    List<SysRole> selectRoles();
}
