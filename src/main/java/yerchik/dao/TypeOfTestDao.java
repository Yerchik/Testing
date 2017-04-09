package yerchik.dao;

import yerchik.entity.Subject;
import yerchik.entity.TypeOfTest;

import java.util.List;

/**
 * Created by Yerchik on 24.03.2017.
 */
public interface TypeOfTestDao {

    void add(TypeOfTest typeOfTest);

    void edit(TypeOfTest typeOfTest);

    void delete(String topic, String subject);

    void deleteList(Subject subject);

    TypeOfTest findById(int id);

    TypeOfTest findByTopic(String topic, String subject);

    List<TypeOfTest> findBySubject (String subject);
}
