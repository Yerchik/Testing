package yerchik.dto;

import yerchik.entity.TypeOfTest;

/**
 * Created by Yerchik on 29.03.2017.
 */
public class TypeOfTestDto {
    private String topic;
    private int numberOfQuestions;
    private int numberInADay;
    private int time;

    public TypeOfTestDto() {
    }

    public TypeOfTestDto(String topic, int numberOfQuestions, int numberInADay, int time) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
        this.numberInADay = numberInADay;
        this.time = time;
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

    public static TypeOfTestDto convertToDTO(TypeOfTest typeOfTest){
        return new TypeOfTestDto(typeOfTest.getTopic(), typeOfTest.getNumberOfQuestions(),
                typeOfTest.getNumberInADay(), typeOfTest.getTime());
    }
}
