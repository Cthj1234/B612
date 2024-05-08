package model;
import model.Subject;

import java.util.HashMap;

public class Student {
    private String studentId;
    private String studentName;

    private HashMap<String, Subject> subjectList;
    private HashMap<String, int[]> scoreList;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectList = new HashMap<>();
        this.scoreList = new HashMap<>();
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

    public HashMap<String, int[]> getScoreList(){
        return scoreList;
    }

}
