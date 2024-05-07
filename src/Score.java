public class Score {
    private String scoreId;
    private int studentId;


    public Score(String seq) {
        this.scoreId = seq;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    } // 과목 고유 번호

    public int getStudentId() {
        return studentId;
    }


}
