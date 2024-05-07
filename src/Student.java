import java.util.HashMap;

public class Student {
    private String studentId;
    private String studentName;

    // 수강하는 과목의
    private HashMap<String, String> subjectList;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectList = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public HashMap<String, String> getSubjectList() {
        return subjectList;
    }
}
