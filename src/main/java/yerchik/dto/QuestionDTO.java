package yerchik.dto;

/**
 * Created by Yerchik on 30.03.2017.
 */
public class QuestionDTO {
    private String subject;
    private String topic;
    private String question;
    private String rightAnswer;
    private String wAnswer1;
    private String wAnswer2;
    private String wAnswer3;
    private String wAnswer4;
    private String wAnswer5;

    public QuestionDTO(String subject, String topic, String question, String rightAnswer, String wAnswer1, String wAnswer2, String wAnswer3, String wAnswer4, String wAnswer5) {
        this.subject = subject;
        this.topic = topic;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wAnswer1 = wAnswer1;
        this.wAnswer2 = wAnswer2;
        this.wAnswer3 = wAnswer3;
        this.wAnswer4 = wAnswer4;
        this.wAnswer5 = wAnswer5;
    }

    public QuestionDTO() {
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "subject='" + subject + '\'' +
                ", topic='" + topic + '\'' +
                ", question='" + question + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", wAnswer1='" + wAnswer1 + '\'' +
                ", wAnswer2='" + wAnswer2 + '\'' +
                ", wAnswer3='" + wAnswer3 + '\'' +
                ", wAnswer4='" + wAnswer4 + '\'' +
                ", wAnswer5='" + wAnswer5 + '\'' +
                '}';
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getwAnswer1() {
        return wAnswer1;
    }

    public void setwAnswer1(String wAnswer1) {
        this.wAnswer1 = wAnswer1;
    }

    public String getwAnswer2() {
        return wAnswer2;
    }

    public void setwAnswer2(String wAnswer2) {
        this.wAnswer2 = wAnswer2;
    }

    public String getwAnswer3() {
        return wAnswer3;
    }

    public void setwAnswer3(String wAnswer3) {
        this.wAnswer3 = wAnswer3;
    }

    public String getwAnswer4() {
        return wAnswer4;
    }

    public void setwAnswer4(String wAnswer4) {
        this.wAnswer4 = wAnswer4;
    }

    public String getwAnswer5() {
        return wAnswer5;
    }

    public void setwAnswer5(String wAnswer5) {
        this.wAnswer5 = wAnswer5;
    }
}
