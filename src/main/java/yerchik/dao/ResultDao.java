package yerchik.dao;

import yerchik.entity.Account;
import yerchik.entity.Result;
import yerchik.entity.TypeOfTest;

import java.util.List;

/**
 * Created by Yerchik on 22.03.2017.
 */
public interface ResultDao {
    void add(Result result);

    void deleteList(TypeOfTest typeOfTest);

    void deleteListResultByLogin(String login);

    Result findById(int id);

    List<Result> findByAccount(String login);

    List<Result> findByTopicAndLogin(TypeOfTest typeOfTest, Account account);

    List<Result> findByDate(String date);

    List<Result> findByTopicLoginDate(TypeOfTest typeOfTest, Account account, String date);
}
