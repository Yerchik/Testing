package yerchik.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yerchik on 20.03.2017.
 */
@Entity
public class TypeOfTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false, unique = true)
    private String topic;
    @Column(nullable = false)
    private int numberOfQuestions;
    @Column(nullable = false)
    private int numberInADay;
    @Column(nullable = false)
    private int time;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    public TypeOfTest() {
    }

    public TypeOfTest(String topic, int numberOfQuestions, int numberInADay, int time, Subject subject) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
        this.numberInADay = numberInADay;
        this.time = time;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
