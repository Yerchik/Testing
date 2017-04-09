package yerchik.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yerchik.dao.AccountDao;
import yerchik.dao.ResultDao;
import yerchik.dao.TypeOfTestDao;
import yerchik.dto.ResultDTO;
import yerchik.entity.Date;
import yerchik.entity.Result;
import yerchik.service.ResultService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerchik on 03.04.2017.
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private TypeOfTestDao testDao;

    @Autowired
    private ResultDao resultDao;

    @Autowired
    private AccountDao accountDao;

    @Override
    public void add(ResultDTO dto, String login) {
        Result result = new Result();
        result.setAccount(accountDao.findByLogin(login));
        result.setTest(testDao.findByTopic(dto.getTopic(), dto.getSubject()));
        String date = Date.date();
        result.setDate(date);
        double number = (((double) dto.getNumber() / result.getTest().getNumberOfQuestions()) * 100);
        result.setResultText((int) number);
        try {
            List<Result> resultListData = new ArrayList<>();
            List<Result> resultList = resultDao.findByTopicAndLogin(result.getTest(), result.getAccount());
            for (Result resultData : resultList) {
                if (resultData.getDate().equals(date)) resultListData.add(resultData);
            }
            result.setNumberInADay(resultListData.size() + 1);
        } catch (Exception e) {
            result.setNumberInADay(1);
        }
        resultDao.add(result);
    }

    @Override
    public Result findById(int id) {
        return null;
    }

    @Override
    public List<Result> findByAccount(String login) {
        return resultDao.findByAccount(accountDao.findByLogin(login).getId());
    }

    @Override
    public List<Result> findByTopicAndLogin(String topic, String subject, String login) {
        return resultDao.findByTopicAndLogin(testDao.findByTopic(topic, subject), accountDao.findByLogin(login));
    }

    @Override
    public List<Result> findByTopicLoginDate(String topic, String subject, String login, String date) {
        return resultDao.findByTopicLoginDate(testDao.findByTopic(topic, subject), accountDao.findByLogin(login),
                date);
    }

    @Override
    public List<Result> findByDate(String date) {
        return resultDao.findByDate(date);
    }


}
