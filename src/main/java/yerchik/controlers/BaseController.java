package yerchik.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yerchik.entity.Date;
import yerchik.service.AccountService;
import yerchik.service.QuestionService;
import yerchik.service.ResultService;
import yerchik.service.TypeOfTestService;

import java.security.Principal;

/**
 * Created by Yerchik on 25.03.2017.
 */
@Controller
public class BaseController {

    @Autowired
    TypeOfTestService testService;
    @Autowired
    QuestionService questionService;
    @Autowired
    ResultService resultService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String signIn(){
        return "signIn";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUp(){
        return "signUp";
    }

    @RequestMapping(value = "/addQuestion/{subject},{topic}", method = RequestMethod.GET)
    public String addTest(@PathVariable String subject, @PathVariable String topic, Model model){
        model.addAttribute("subject", subject);
        model.addAttribute("topic", topic);
        int namberOfQuestions;
        try{
             namberOfQuestions = (questionService.findByTapeOfTest(topic, subject).size()
             - testService.findByTopic(topic, subject).getNumberOfQuestions());
             if (namberOfQuestions > -1) namberOfQuestions = questionService.findByTapeOfTest(topic, subject).size();
        }catch (NullPointerException e){
            namberOfQuestions =  0 - testService.findByTopic(topic, subject).getNumberOfQuestions();
        }
        model.addAttribute("namberOfQuestions", namberOfQuestions);
        return "addQuestion";
    }

    @RequestMapping(value = "/editTest", method = RequestMethod.GET)
    public String editTest(){
        return "editTest";
    }

    @RequestMapping(value = "/passATest/{subject},{topic}", method = RequestMethod.GET)
    public String passATest(@PathVariable String subject, @PathVariable String topic, Model model, Principal principal){
        int numberInDay = testService.findByTopic(topic, subject).getNumberInADay();
        String login = principal.getName();
        String date = Date.date();
        try {
            if (numberInDay <= resultService.findByTopicLoginDate(topic, subject, login, date).size()){
                return "redirect:/";
            }
        }catch (Exception e){}
        int number = testService.findByTopic(topic, subject).getNumberOfQuestions();
        model.addAttribute("number", number);
        model.addAttribute("subject", subject);
        model.addAttribute("topic", topic);
        int time = testService.findByTopic(topic, subject).getTime();
        model.addAttribute("time", time);
        return "passATest";
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public String verifyAccount(@PathVariable String login){
        accountService.verify(login);
        return "signIn";
    }

}
