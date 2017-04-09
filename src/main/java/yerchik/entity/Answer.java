package yerchik.entity;

import javax.persistence.*;

/**
 * Created by Yerchik on 20.03.2017.
 */
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false)
    private String answerText;
    @Column(nullable = false)
    private boolean value;
    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Answer(String answerText, boolean value, Question question) {
        this.answerText = answerText;
        this.value = value;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
