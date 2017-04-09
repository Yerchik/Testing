package yerchik.entity;

import javax.persistence.*;

/**
 * Created by Yerchik on 20.03.2017.
 */
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false)
    private int resultText;
    @Column(nullable = false)
    private int numberInADay;
    @Column(nullable = false)
    private String date;


    @ManyToOne
    private TypeOfTest test;
    @ManyToOne
    private Account account;

    public Result() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultText() {
        return resultText;
    }

    public void setResultText(int resultText) {
        this.resultText = resultText;
    }

    public int getNumberInADay() {
        return numberInADay;
    }

    public void setNumberInADay(int numberInADay) {
        this.numberInADay = numberInADay;
    }

    public TypeOfTest getTest() {
        return test;
    }

    public void setTest(TypeOfTest test) {
        this.test = test;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Result(int resultText, int numberInADay, String date, TypeOfTest test, Account account) {
        this.resultText = resultText;
        this.numberInADay = numberInADay;
        this.date = date;
        this.test = test;
        this.account = account;
    }
}
