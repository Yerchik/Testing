package yerchik.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yerchik on 20.03.2017.
 */
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false, unique = true)
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private List<TypeOfTest> tests;



    public Subject() {
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<TypeOfTest> getTests() {
        return tests;
    }

    public void setTests(List<TypeOfTest> tests) {
        this.tests = tests;
    }
}
