package yerchik.dao;

import yerchik.entity.Role;

/**
 * Created by Yerchik on 22.03.2017.
 */
public interface RoleDao {
    Role findRoleByName(String name);
}
