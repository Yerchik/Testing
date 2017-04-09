package yerchik.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yerchik.dao.SubjectDao;
import yerchik.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Yerchik on 24.03.2017.
 */
@Repository
public class SubjectDaoImpl implements SubjectDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(Subject subject) {
        entityManager.persist(subject);
    }

    @Transactional
    public void delete(Subject subject) {
        entityManager.remove(entityManager.createQuery("SELECT s FROM Subject s WHERE s = :subject")
                .setParameter("subject", subject).getSingleResult());
    }

    @Transactional
    public Subject findById(int id) {
        return entityManager.find(Subject.class, id);
    }

    @Transactional
    public Subject findBySubject(String subject) {
        return (Subject)entityManager.createQuery("SELECT s FROM Subject s WHERE s.subjectName = :subject")
                .setParameter("subject", subject).getSingleResult();
    }

    @Transactional
    public List<Subject> findAllSubject() {
        return entityManager.createQuery("SELECT s FROM Subject s").getResultList();
    }
}
