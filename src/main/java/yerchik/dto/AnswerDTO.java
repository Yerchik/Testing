package yerchik.dto;

import yerchik.entity.Answer;

/**
 * Created by Yerchik on 02.04.2017.
 */
public class AnswerDTO {
    private String answerText;

    private boolean value;


    public AnswerDTO() {
    }

    public AnswerDTO(String answerText, boolean value) {
        this.answerText = answerText;
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerDTO)) return false;

        AnswerDTO answerDTO = (AnswerDTO) o;

        if (isValue() != answerDTO.isValue()) return false;
        return getAnswerText() != null ? getAnswerText().equals(answerDTO.getAnswerText()) : answerDTO.getAnswerText() == null;
    }

    @Override
    public int hashCode() {
        int result = getAnswerText() != null ? getAnswerText().hashCode() : 0;
        result = 31 * result + (isValue() ? 1 : 0);
        return result;
    }

    public static AnswerDTO convertAnswerToDTO(Answer answer){
        return new AnswerDTO(answer.getAnswerText(), answer.isValue());
    }
}
