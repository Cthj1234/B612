package model;

import java.util.HashMap;


// 각 과목별 회차 점수를 저장 하는 scoreList 객체.

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

    public HashMap<String, int[]> getScoreList() {
        return scoreList;
    }
}