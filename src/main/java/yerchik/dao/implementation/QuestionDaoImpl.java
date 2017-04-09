package yerchik.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yerchik.dao.QuestionDao;
import yerchik.entity.Question;
import yerchik.entity.TypeOfTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yerchik on 23.03.2017.
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(Question question) {
        entityManager.persist(question);
    }

    @Transactional
    public void deleteList(TypeOfTest test) {
        List<Question> list =  entityManager.createQuery("SELECT l FROM Question l WHERE l.test = :test")
                .setParameter("test", test).getResultList();
        for (Question question : list) {
            entityManager.remove(question);
        }
    }

    @Transactional
    public Question findById(int id) {
        return entityManager.find(Question.class, id);
    }

    @Transactional
    public Question findByQuestion(String question) {
        return (Question) entityManager.createQuery("select q FROM Question q where q.questionText = :question")
                .setParameter("question", question).getSingleResult();
    }

    @Transactional
    public List<Question> findByTapeOfTest(TypeOfTest test) {
        List<Question> list =  entityManager.createQuery("SELECT l FROM Question l WHERE l.test = :test")
                .setParameter("test", test).getResultList();
        Collections.shuffle(list);
        return list;
    }
}
