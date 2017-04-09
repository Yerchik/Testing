package yerchik.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yerchik.dao.AccountDao;
import yerchik.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Yerchik on 23.03.2017.
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(Account account) {
        entityManager.persist(account);
    }

    @Transactional
    public void edit(Account account) {
        entityManager.merge(account);
    }


    @Transactional
    public void delete(String login) {
        entityManager.remove( entityManager.createQuery("select c FROM Account c where c.login = :login or c.email = :email")
                .setParameter("login", login).setParameter("email", login).getSingleResult());
    }

    @Transactional
    public Account findById(int id) {
        return entityManager.find(Account.class, id);
    }

    @Transactional
    public Account findByLogin(String login) {
        return (Account) entityManager.createQuery("select c FROM Account c where c.login = :login or c.email = :email")
                .setParameter("login", login).setParameter("email", login).getSingleResult();
    }

    @Transactional
    public List<Account> findAllUsers(String role) {
        return entityManager.createQuery("select c FROM Account c where c.role.name = :role").setParameter("role", role).getResultList();
    }

    @Transactional
    public Account findByNameAndSecondname(String name, String secondName) {
        return (Account) entityManager.createQuery("select c FROM Account c where c.name = :name AND c.secondName = :secondName")
                .setParameter("name", name).setParameter("secondName", secondName).getSingleResult();
    }
}
