package yerchik.dto;

import yerchik.entity.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerchik on 04.04.2017.
 */
public class ShowResultDTO {
    private String subject;
    private String topic;
    private int resultTest;
    private String date;

    public ShowResultDTO() {
    }

    public ShowResultDTO(String subject, String topic, int resultTest, String date) {
        this.subject = subject;
        this.topic = topic;
        this.resultTest = resultTest;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getResultTest() {
        return resultTest;
    }

    public void setResultTest(int resultTest) {
        this.resultTest = resultTest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static ShowResultDTO convertToDTO(Result result){
        return new ShowResultDTO(result.getTest().getSubject().getSubjectName(), result.getTest().getTopic(),
                result.getResultText(), result.getDate());
    }

    public static List<ShowResultDTO> convertListToDTO(List<Result> results){
        List<ShowResultDTO> dtos = new ArrayList<>();
        for (Result result : results) {
            dtos.add(ShowResultDTO.convertToDTO(result));
        }
        return dtos;
    }
}
