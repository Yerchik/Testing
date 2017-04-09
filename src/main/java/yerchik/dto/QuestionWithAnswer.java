package yerchik.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import yerchik.entity.Answer;
import yerchik.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yerchik on 02.04.2017.
 */
public class QuestionWithAnswer {
    private String questionText;

    private List<AnswerDTO> answers;

    public QuestionWithAnswer() {
    }

    public QuestionWithAnswer(String questionText, List<AnswerDTO> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    public static QuestionWithAnswer convertToDTO(Question question) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer answer : question.getAnswers()) {
            answerDTOS.add(AnswerDTO.convertAnswerToDTO(answer));
        }
        return new QuestionWithAnswer(question.getQuestionText(), answerDTOS);
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof QuestionWithAnswer)) return false;

        QuestionWithAnswer that = (QuestionWithAnswer) o;

        return new EqualsBuilder()
                .append(getQuestionText(), that.getQuestionText())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionText());
    }
}
