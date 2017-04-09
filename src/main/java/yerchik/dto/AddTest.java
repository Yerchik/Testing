package yerchik.dto;

import yerchik.entity.Subject;
import yerchik.entity.TypeOfTest;

/**
 * Created by Yerchik on 28.03.2017.
 */
public class AddTest {
    private String topic;
    private int numberOfQuestions;
    private int numberInADay;
    private int time;
    private String subject;

    public AddTest() {
    }

    public AddTest(String topic, int numberOfQuestions, int numberInADay, int time, String subject) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
        this.numberInADay = numberInADay;
        this.time = time;
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberInADay() {
        return numberInADay;
    }

    public void setNumberInADay(int numberInADay) {
        this.numberInADay = numberInADay;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TypeOfTest convertToEntity(){
        return new TypeOfTest(this.topic, this.numberOfQuestions, this.numberInADay, this.time, new Subject());
    }
}
