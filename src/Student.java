public class Student {
    private String studentId;
    private String studentName;
    private String studentSubject;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.studentSubject = studentSubject;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSubject() {return studentSubject;}

}
