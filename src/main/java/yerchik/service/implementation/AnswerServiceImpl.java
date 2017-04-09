package yerchik.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yerchik.dao.AnswerDao;
import yerchik.entity.Answer;
import yerchik.service.AnswerService;

import java.util.List;

/**
 * Created by Yerchik on 30.03.2017.
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public void add(List<Answer> answers) {
        answerDao.add(answers);
    }

    @Override
    public Answer findById(int id) {
        return answerDao.findById(id);
    }

    @Override
    public List<Answer> findByQuestion(int id) {
        return answerDao.findByQuestion(id);
    }
}
