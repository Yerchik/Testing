package yerchik.service;

import yerchik.dto.ResultDTO;
import yerchik.entity.Result;

import java.util.List;

/**
 * Created by Yerchik on 03.04.2017.
 */
public interface ResultService {

    void add(ResultDTO dto, String login);

    Result findById(int id);

    List<Result> findByAccount(String login);

    List<Result> findByTopicAndLogin(String topic,String subject, String login);

    List<Result> findByTopicLoginDate(String topic,String subject, String login, String date);

    List<Result> findByDate(String date);
}
