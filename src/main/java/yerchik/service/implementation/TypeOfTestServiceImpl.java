package yerchik.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yerchik.dao.*;
import yerchik.dto.AddTest;
import yerchik.entity.Question;
import yerchik.entity.Subject;
import yerchik.entity.TypeOfTest;
import yerchik.service.TypeOfTestService;

import java.util.List;

/**
 * Created by Yerchik on 28.03.2017.
 */
@Service
public class TypeOfTestServiceImpl implements TypeOfTestService {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private TypeOfTestDao testDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private ResultDao resultDao;

    @Override
    public void add(AddTest dto) {

        TypeOfTest typeOfTest = dto.convertToEntity();
        Subject subject;
        try {
            subject = subjectDao.findBySubject(dto.getSubject());
        }catch (Exception e ){
            subject = new Subject(dto.getSubject());
            subjectDao.add(subject);
        }

        typeOfTest.setSubject(subject);
        testDao.add(typeOfTest);

    }

    @Override
    public void edit(AddTest dto) {

    }

    @Override
    public void delete(String topic, String subject) {
        TypeOfTest typeOfTest = testDao.findByTopic(topic, subject);
        try {resultDao.deleteList(typeOfTest);
        }catch (Exception e){}
        try {
            for (Question question : questionDao.findByTapeOfTest(typeOfTest)) {
                answerDao.deleteList(question);
            }
            questionDao.deleteList(typeOfTest);
        }catch (Exception e){}
        testDao.delete( topic,  subject);
    }

    @Override
    public void deleteList(Subject subject) {

        for (TypeOfTest typeOfTest : testDao.findBySubject(subject.getSubjectName())) {
            try {
                resultDao.deleteList(typeOfTest);
            }catch (Exception e){}
            try {
                for (Question question : questionDao.findByTapeOfTest(typeOfTest)) {
                    answerDao.deleteList(question);
                }
                questionDao.deleteList(typeOfTest);
            }catch (Exception e){}
        }
        testDao.deleteList(subject);
        subjectDao.delete(subject);
    }


    @Override
    public TypeOfTest findById(int id) {
        return testDao.findById(id);
    }

    @Override
    public TypeOfTest findByTopic(String topic,String subject) {
        return testDao.findByTopic(topic, subject);
    }

    @Override
    public List<TypeOfTest> findBySubject(String subject) {
        return testDao.findBySubject(subject);
    }
}
