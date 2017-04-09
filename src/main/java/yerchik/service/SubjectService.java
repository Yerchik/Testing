package yerchik.service;

import yerchik.entity.Subject;

import java.util.List;

/**
 * Created by Yerchik on 28.03.2017.
 */
public interface SubjectService {
    void add(String subject);

    Subject findBySubject(String subject);

    List<Subject> findAllSubject();
}
