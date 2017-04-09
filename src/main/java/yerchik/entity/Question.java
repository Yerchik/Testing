package yerchik.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yerchik on 20.03.2017.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false)
    private String questionText;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @ManyToOne
    private TypeOfTest test;

    public Question() {
    }

    public Question(String questionText, TypeOfTest test) {
        this.questionText = questionText;
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public TypeOfTest getTest() {
        return test;
    }

    public void setTest(TypeOfTest test) {
        this.test = test;
    }
}
