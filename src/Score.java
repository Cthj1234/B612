public class Score {
    private String scoreId;
    private String subjectName;
    private String studentId;
    private String Grade;
    private int time = 0;


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
    public int getTime() {
        return time;
    }

    public void plusTime() {
        this.time += 1;
    }

    public void changeScore(String score) {
        this.scoreId = score;
    }

    public void changeGrade(String grade) {
        this.Grade = grade;
    }

}
