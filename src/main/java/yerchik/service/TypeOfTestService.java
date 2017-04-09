package yerchik.service;

import yerchik.dto.AddTest;
import yerchik.entity.Subject;
import yerchik.entity.TypeOfTest;

import java.util.List;

/**
 * Created by Yerchik on 28.03.2017.
 */
public interface TypeOfTestService {
    void add(AddTest dto);

    void edit(AddTest dto);

    void delete(String topic, String subject);

    void deleteList(Subject subject);

    TypeOfTest findById(int id);

    TypeOfTest findByTopic(String topic, String subject);
    List<TypeOfTest> findBySubject (String  subject);
}
