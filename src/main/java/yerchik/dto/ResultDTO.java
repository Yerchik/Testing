package yerchik.dto;

/**
 * Created by Yerchik on 03.04.2017.
 */
public class ResultDTO {
    private int number;
    private String subject;
    private String topic;

    public ResultDTO() {
    }

    public ResultDTO(int number, String subject, String topic) {
        this.number = number;
        this.subject = subject;
        this.topic = topic;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
}
