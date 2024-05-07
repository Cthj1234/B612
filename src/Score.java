public class Score {
    private String scoreId;
    private String subjectName;
    private String studentId;
    private String Grade;


    public Score(String seq, String subjectName, String studentId, String grade) {
        this.scoreId = seq;
        this.subjectName = subjectName;
        this.studentId = studentId;
        this.Grade = grade;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    } // 과목 고유 번호

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public String getGrade() {
        return Grade;
    }

}
