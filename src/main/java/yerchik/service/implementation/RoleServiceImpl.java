package yerchik.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yerchik.dao.RoleDao;
import yerchik.entity.Role;
import yerchik.service.RoleService;

/**
 * Created by Yerchik on 25.03.2017.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }
}
