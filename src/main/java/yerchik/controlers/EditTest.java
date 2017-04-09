package yerchik.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yerchik.dto.AddTest;
import yerchik.dto.QuestionDTO;
import yerchik.dto.SelectSubject;
import yerchik.dto.TypeOfTestDto;
import yerchik.entity.Subject;
import yerchik.entity.TypeOfTest;
import yerchik.service.QuestionService;
import yerchik.service.SubjectService;
import yerchik.service.TypeOfTestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerchik on 28.03.2017.
 */
@Controller
public class EditTest {
    @Autowired
    TypeOfTestService typeOfTestService;

    @Autowired
    QuestionService questionService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/addTest", method = RequestMethod.POST)
    @ResponseBody
    public void addTest(@RequestBody AddTest dto){
        typeOfTestService.add(dto);
    }

    @RequestMapping(value = "/getSubjects", method = RequestMethod.GET)
    @ResponseBody
    public List<SelectSubject> getAllSubject(){
        List<Subject> subjects = subjectService.findAllSubject();
        List<SelectSubject> selectSubjects = new ArrayList<>();
        for (Subject subject:subjects) {
            selectSubjects.add(SelectSubject.convertToDTO(subject));
        }
        return selectSubjects;
    }

    @RequestMapping(value = "/getTapeBy/{subject}", method = RequestMethod.GET)
    @ResponseBody
    public List<TypeOfTestDto> testBySubject(@PathVariable String subject){
        List<TypeOfTest> typeOfTests= typeOfTestService.findBySubject(subject);
        List<TypeOfTestDto> dtos = new ArrayList<>();
        for (TypeOfTest typeOfTest:typeOfTests) {
            dtos.add(TypeOfTestDto.convertToDTO(typeOfTest));
        }
        return dtos;
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    @ResponseBody
    public void addTest(@RequestBody QuestionDTO dto){
        questionService.add(dto);
    }

    @RequestMapping(value = "/delete/{subject}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteSubject(@PathVariable String subject){
        typeOfTestService.deleteList(subjectService.findBySubject(subject));

    }

    @RequestMapping(value = "/deletetopic/{subject},{topic}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteTopicBySubject(@PathVariable String subject, @PathVariable String topic){
        typeOfTestService.delete(topic, subject);

    }
}
