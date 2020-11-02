package ink.chevro.admin.service.system.impl;

import ink.chevro.admin.bussiness.dao.system.impl.SysMenuDAO;
import ink.chevro.admin.service.system.ISysMenuService;
import ink.chevro.dto.system.MenuDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:30 2020-09-03
 **/
@Service
public class SysMenuService implements ISysMenuService {

    @Resource
    private SysMenuDAO sysMenuDAO;

    @Override
    public List<MenuDTO> getMenus(String role) {
        return sysMenuDAO.getMenusAll(role);
    }
}
