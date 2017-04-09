package yerchik.service;

import yerchik.entity.Answer;

import java.util.List;

/**
 * Created by Yerchik on 30.03.2017.
 */
public interface AnswerService {
    void add(List<Answer> answers);

    Answer findById(int id);

    List<Answer> findByQuestion(int id);
}
