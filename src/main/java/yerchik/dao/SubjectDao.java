package yerchik.dao;

import yerchik.entity.Subject;

import java.util.List;

/**
 * Created by Yerchik on 22.03.2017.
 */
public interface SubjectDao {
    void add(Subject subject);
    void delete(Subject subject);
    Subject findById(int id);
    Subject findBySubject(String subject);
    List<Subject> findAllSubject();
}
