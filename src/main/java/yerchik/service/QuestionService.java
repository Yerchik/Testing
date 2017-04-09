package yerchik.service;

import yerchik.dto.QuestionDTO;
import yerchik.dto.QuestionWithAnswer;
import yerchik.entity.Question;

import java.util.List;

/**
 * Created by Yerchik on 30.03.2017.
 */
public interface QuestionService {
    void add(QuestionDTO dto);

    Question findById(int id);

    Question findByQuestion(String question);

    List<Question> findByTapeOfTest(String topic, String subject);

    List<QuestionWithAnswer> findByTapeOfTestDTO(String topic, String subject);
}
