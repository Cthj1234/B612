import java.util.HashMap;
import java.util.Scanner;

public class Student {
    private String studentId;
    private String studentName;

    private HashMap<String, Subject> subjectList;

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

    public HashMap<String, Subject> getSubjectList() {
        return subjectList;
    }

}
