package yerchik.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yerchik.dao.RoleDao;
import yerchik.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Yerchik on 23.03.2017.
 */
@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public Role findRoleByName(String name) {
        return (Role) entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name").setParameter("name", name).getSingleResult();
    }
}
