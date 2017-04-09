package yerchik.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yerchik.dao.AnswerDao;
import yerchik.dao.QuestionDao;
import yerchik.dao.SubjectDao;
import yerchik.dao.TypeOfTestDao;
import yerchik.dto.QuestionDTO;
import yerchik.dto.QuestionWithAnswer;
import yerchik.entity.Answer;
import yerchik.entity.Question;
import yerchik.entity.TypeOfTest;
import yerchik.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerchik on 30.03.2017.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private TypeOfTestDao testDao;

    @Autowired
    private AnswerDao answerDao;

    @Override
    public void add(QuestionDTO dto) {

        TypeOfTest typeOfTest = testDao.findByTopic(dto.getTopic(), dto.getSubject());
        Question questionAdd = new Question(dto.getQuestion(), typeOfTest);
        questionDao.add(questionAdd);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(dto.getRightAnswer(), true, questionAdd));
        answers.add(new Answer(dto.getwAnswer1(), false, questionAdd));

        if (!dto.getwAnswer2().equals("")) answers.add(new Answer(dto.getwAnswer2(), false, questionAdd));
        if (!dto.getwAnswer3().equals("")) answers.add(new Answer(dto.getwAnswer3(), false, questionAdd));
        if (!dto.getwAnswer4().equals("")) answers.add(new Answer(dto.getwAnswer4(), false, questionAdd));
        if (!dto.getwAnswer4().equals("")) answers.add(new Answer(dto.getwAnswer5(), false, questionAdd));
        answerDao.add(answers);
    }



    @Override
    public Question findById(int id) {
        return questionDao.findById(id);
    }

    @Override
    public Question findByQuestion(String question) {
        return questionDao.findByQuestion(question);
    }

    @Override
    public List<QuestionWithAnswer> findByTapeOfTestDTO(String topic, String subject) {
        int sizeQuestionList = testDao.findByTopic(topic, subject).getNumberOfQuestions();
        List<QuestionWithAnswer> allQuestionWithAnswers = new ArrayList<>();
        List<QuestionWithAnswer> questions = new ArrayList<>();
        for (Question question: questionDao.findByTapeOfTest(testDao.findByTopic(topic, subject))) {
            question.setAnswers(answerDao.findByQuestion( question.getId()));
            questions.add(QuestionWithAnswer.convertToDTO(question));
        }

        for (int i = 0; i < sizeQuestionList; i++){
            allQuestionWithAnswers.add(questions.get(i));
        }
//        do {
//            int i = (int)(Math.random() *(questions.size()));
//            if (!allQuestionWithAnswers.contains(questions.get(i))){
//                allQuestionWithAnswers.add(questions.get(i));
//            }
//
//        }while (allQuestionWithAnswers.size() < sizeQuestionList);
        return allQuestionWithAnswers;

    }

    public List<Question> findByTapeOfTest(String topic, String subject){
        return questionDao.findByTapeOfTest(testDao.findByTopic(topic,subject));
    }
}
