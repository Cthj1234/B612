public class Score {
    private String scoreId;
    private String subjectName;
    private String studentId;
    private String Grade;
    private int time = 1; // 이게 그 횟수 객체인데 얘는 생성자에 안들어가고 따로 만들어져서 네네


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


    public void changeScore(String score) {
        this.scoreId = score;
    }

    public void changeGrade(String grade) {
        this.Grade = grade;
    }

}
