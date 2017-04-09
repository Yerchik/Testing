package yerchik.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yerchik.dao.AnswerDao;
import yerchik.entity.Answer;
import yerchik.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yerchik on 23.03.2017.
 */
@Repository
public class AnswerDaoImpl implements AnswerDao {
    @PersistenceContext(unitName = "Main")
    private EntityManager entityManager;

    @Transactional
    public void add(List<Answer> answers) {
        for (Answer answer : answers) {
            entityManager.persist(answer);
        }
    }

    @Transactional
    public void deleteList(Question question) {
        List<Answer> answers = entityManager.createQuery("SELECT a FROM Answer a WHERE a.question = :question").
                setParameter("question", question).getResultList();
        for (Answer answer : answers) {
            entityManager.remove(answer);}
        }


    @Transactional
    public Answer findById(int id)   {
        return entityManager.find(Answer.class, id);
    }

    @Transactional
    public List<Answer> findByQuestion(int id) {
        List<Answer> answers = entityManager.createQuery("SELECT a FROM Answer a WHERE a.question.id = :id").
                setParameter("id", id).getResultList();
        Collections.shuffle(answers);
        return answers;
    }
}
