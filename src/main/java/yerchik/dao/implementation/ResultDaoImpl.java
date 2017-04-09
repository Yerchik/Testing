package yerchik.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yerchik.dao.ResultDao;
import yerchik.entity.Account;
import yerchik.entity.Result;
import yerchik.entity.TypeOfTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Yerchik on 23.03.2017.
 */
@Repository
public class ResultDaoImpl implements ResultDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(Result result) {
        entityManager.persist(result);
    }

    @Transactional
    public void deleteList(TypeOfTest typeOfTest) {
        List<Result> resultList = entityManager.createQuery("SELECT r FROM Result r WHERE r.test =:typeOfTest")
                .setParameter("typeOfTest", typeOfTest).getResultList();
        for (Result result : resultList) {
            entityManager.remove(result);
        }
    }

    @Transactional
    public void deleteListResultByLogin(String login) {
        List<Result> resultList = entityManager.createQuery("select r from Result r where r.account.login = :login OR r.account.email = :login")
                .setParameter("login", login).getResultList();
        for (Result result : resultList) {
            entityManager.remove(result);
        }
    }


    @Transactional
    public Result findById(int id) {
        return entityManager.find(Result.class, id);
    }

    @Transactional
    public List<Result> findByAccount(String login) {
        return entityManager.createQuery("select r from Result r where r.account.login = :login OR r.account.email = :login")
                .setParameter("login", login).getResultList();
    }

    @Transactional
    public List<Result> findByTopicAndLogin(TypeOfTest typeOfTest, Account account) {
        return entityManager.createQuery("SELECT r FROM Result r WHERE r.test =:typeOfTest AND r.account =:account")
                .setParameter("typeOfTest", typeOfTest).setParameter("account", account).getResultList();
    }

    @Transactional
    public List<Result> findByDate(String date) {
        return entityManager.createQuery("SELECT r FROM Result r WHERE r.date =:date")
                .setParameter("date", date).getResultList();
    }

    @Transactional
    public List<Result> findByTopicLoginDate(TypeOfTest typeOfTest, Account account, String date) {
        return entityManager.createQuery("SELECT r FROM Result r WHERE r.test =:typeOfTest AND r.account =:account AND r.date =:date")
                .setParameter("typeOfTest", typeOfTest).setParameter("account", account).setParameter("date", date).getResultList();
    }
}
