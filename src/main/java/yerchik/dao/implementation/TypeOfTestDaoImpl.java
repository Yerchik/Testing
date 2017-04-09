package yerchik.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yerchik.dao.TypeOfTestDao;
import yerchik.entity.Subject;
import yerchik.entity.TypeOfTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Yerchik on 24.03.2017.
 */
@Repository
public class TypeOfTestDaoImpl implements TypeOfTestDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;
    @Transactional
    public void add(TypeOfTest typeOfTest) {
        entityManager.persist(typeOfTest);
    }

    @Transactional
    public void edit(TypeOfTest typeOfTest) {
        entityManager.merge(typeOfTest);
    }



    @Transactional
    public void delete(String topic, String subject) {
        entityManager.remove(entityManager.createQuery("SELECT t FROM TypeOfTest t WHERE t.topic = :topic AND t.subject.subjectName = :subject")
                .setParameter("topic", topic).setParameter("subject", subject).getSingleResult());
    }

    @Transactional
    public void deleteList(Subject subject) {
        List<TypeOfTest> typeOfTests = entityManager.createQuery("SELECT t FROM TypeOfTest t WHERE t.subject = :subject")
                .setParameter("subject", subject).getResultList();
        for (TypeOfTest typeOfTest : typeOfTests) {
            entityManager.remove(typeOfTest);
        }
    }


    @Transactional
    public TypeOfTest findById(int id) {
        return entityManager.find(TypeOfTest.class, id);
    }

    @Transactional
    public TypeOfTest findByTopic(String topic, String subject) {
        return (TypeOfTest) entityManager.createQuery("SELECT t FROM TypeOfTest t WHERE t.topic = :topic AND t.subject.subjectName = :subject")
                .setParameter("topic", topic).setParameter("subject", subject).getSingleResult();
    }

    @Transactional
    public List<TypeOfTest> findBySubject(String  subject) {
        return entityManager.createQuery("SELECT t FROM TypeOfTest t WHERE t.subject.subjectName = :subject")
                .setParameter("subject", subject).getResultList();
    }
}
