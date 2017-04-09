package yerchik.service;

import yerchik.entity.Role;

/**
 * Created by Yerchik on 25.03.2017.
 */
public interface RoleService {
    Role findRoleByName(String name);
}
