package yerchik.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yerchik.dao.SubjectDao;
import yerchik.entity.Subject;
import yerchik.service.SubjectService;

import java.util.List;

/**
 * Created by Yerchik on 28.03.2017.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public void add(String subject) {
        subjectDao.add(new Subject(subject));
    }

    @Override
    public Subject findBySubject(String subject) {
        return subjectDao.findBySubject(subject);
    }

    @Override
    public List<Subject> findAllSubject() {
        return subjectDao.findAllSubject();
    }
}
