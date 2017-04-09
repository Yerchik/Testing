package yerchik.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yerchik.dto.QuestionWithAnswer;
import yerchik.dto.ResultDTO;
import yerchik.entity.Date;
import yerchik.service.QuestionService;
import yerchik.service.ResultService;
import yerchik.service.TypeOfTestService;

import java.security.Principal;
import java.util.List;

/**
 * Created by Yerchik on 29.03.2017.
 */
@Controller
public class PassATest {

    @Autowired
    QuestionService questionService;

    @Autowired
    ResultService resultService;

    @Autowired
    TypeOfTestService testService;


    @RequestMapping(value = "/getQuestions/{subject},{topic}", method = RequestMethod.GET)
    @ResponseBody
    public List<QuestionWithAnswer> getQuestions(@PathVariable String subject, @PathVariable String topic){
        List<QuestionWithAnswer> list = questionService.findByTapeOfTestDTO(topic, subject);
        return list;
    }

    @RequestMapping(value = "/addResult/", method = RequestMethod.POST)
    @ResponseBody
    public void addTest(@RequestBody ResultDTO dto, Principal principal){
        String login = principal.getName();
        resultService.add(dto, login);
    }

    @RequestMapping(value = "/numberLeft/{subject},{topic}", method = RequestMethod.GET)
    @ResponseBody
    public int numberLeft(@PathVariable String subject, @PathVariable String topic, Principal principal){
        int numberLeft;
        try{
            if (testService.findByTopic(topic, subject).getNumberOfQuestions() > questionService.findByTapeOfTest(topic, subject).size()) {
                numberLeft = -1;
                return numberLeft;}
        }catch (Exception e){
            numberLeft = -1;
            return numberLeft;}
        try{
             numberLeft = testService.findByTopic(topic, subject).getNumberInADay() -
                    resultService.findByTopicLoginDate(topic, subject, principal.getName(), Date.date()).size();
        }catch (Exception e){
             numberLeft = testService.findByTopic(topic, subject).getNumberInADay();
        }

        return numberLeft;
    }


}
