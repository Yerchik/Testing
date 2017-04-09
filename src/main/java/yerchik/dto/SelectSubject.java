package yerchik.dto;

import yerchik.entity.Subject;

/**
 * Created by Yerchik on 28.03.2017.
 */
public class SelectSubject {

    private String subjectName;

    public SelectSubject() {
    }

    public SelectSubject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public static SelectSubject convertToDTO(Subject subject){
        return new SelectSubject(subject.getSubjectName());
    }
}
