import java.util.Scanner;

public class InquiryGrade {
    static Scanner sc = new Scanner(System.in);
    public void GetScoreBySubject() throws InterruptedException {
        int StudentNumber = GetStudentId();
        System.out.println("조회할 과목을 선택해 주세요. (숫자로 입력)");
        System.out.println("1.Java 2.객체지향 3.Spring 4. JPA 5. MySQL");
        System.out.println("6.디자인 패턴 7. Spring Security 8. Redis 9. MongoDB");
        int input = sc.nextInt();
        switch (input) {
            case 1:
                System.out.println("Java 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 2:
                System.out.println("객체지향 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 3:
                System.out.println("Spring 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 4:
                System.out.println("JPA 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 5:
                System.out.println("MySQL 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 6:
                System.out.println("디자인 패턴 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 7:
                System.out.println("Spring Seucrity 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 8:
                System.out.println("Redis 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기
            case 9:
                System.out.println("MongoDB 과목 성적을 불러오는 중입니다.");
                wait(3);
                //학생의 과목 성적 목록 불러오기

        }
    }
    private static int GetStudentId() {
        System.out.println("조회할 수강생의 번호를 입력하세요.");
        return sc.nextInt();
    }
}
