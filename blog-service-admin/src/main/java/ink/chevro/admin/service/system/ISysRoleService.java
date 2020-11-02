package ink.chevro.admin.service.system;


import ink.chevro.admin.entity.system.SysRole;
import ink.chevro.dto.system.RoleDTO;

import java.util.List;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:16 2020-07-21
 **/
public interface ISysRoleService {

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
     * 查询角色相关权限信息
     *
     * @return
     */
    List<RoleDTO> selectRoles();

    /**
     * 修改角色信息
     *
     * @param roleKey
     * @return
     */
    int updateRoles(String roleKey, RoleDTO roleDTO);


}
