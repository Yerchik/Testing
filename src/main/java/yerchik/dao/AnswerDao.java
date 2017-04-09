package yerchik.dao;

import yerchik.entity.Answer;
import yerchik.entity.Question;

import java.util.List;

/**
 * Created by Yerchik on 22.03.2017.
 */
public interface AnswerDao {
    void add(List<Answer> answers);

    void deleteList(Question question);

    Answer findById(int id);

    List<Answer> findByQuestion(int id);


}
