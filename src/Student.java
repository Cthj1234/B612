public class Student {
    private String studentId;
    private String studentName;
    private String studentSubject;
    private String status;

    public Student(String seq, String studentName, String studentSubject, String status) {
        this.studentId = seq;
        this.studentName = studentName;
        this.studentSubject = studentSubject;
        this.status = status;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSubject() {return studentSubject;}

    public String getStatus() { return status;}

    public void changeStatus(String status) {
        this.status = status;
    }

    public void changeName(String studentName) {
        this.studentName = studentName;
    }

}
