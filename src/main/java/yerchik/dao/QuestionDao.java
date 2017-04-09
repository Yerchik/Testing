package yerchik.dao;

import yerchik.entity.Question;
import yerchik.entity.TypeOfTest;

import java.util.List;

/**
 * Created by Yerchik on 22.03.2017.
 */
public interface QuestionDao {
    void add(Question question);

    void deleteList(TypeOfTest test);

    Question findById(int id);

    Question findByQuestion(String question);

    List<Question> findByTapeOfTest(TypeOfTest test);
}
