import java.util.*;

class Main {
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData(); // 데이터 입력 함수
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("오류 발생으로 인해 프로그램을 종료합니다.");
        }
    }

    private static void setInitData() {
        studentStore = new ArrayList<>(); // 여기에 이제 그 뭐더라 학생 번호가 다르게 들어올거란 말이여?
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    private static void displayMainView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        System.out.println("등록할 과목을 선택해 주십시오.");
        int i = 1;
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType() == SUBJECT_TYPE_MANDATORY) {
                System.out.print(i + "." + subject.getSubjectName() + " ");
                i++;
            }
        }
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType() == SUBJECT_TYPE_CHOICE) {
                System.out.print(i + "." + subject.getSubjectName() + " ");
                i++;
            }
        }
        String subName = sc.next();


        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, subName);


        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // Iterator 써서 studentStore에 저장된 모든 학생들 정보 훑어서 수강생 이름이랑 번호 나오게
        for (Student student : studentStore) {
            System.out.println("수강생 이름 : " + student.getStudentName() + " 수강생 번호 : " + student.getStudentId());
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    private static String getSubject() {
        System.out.println("불러올 과목을 선택해 주세요.");
        int j = 1;
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType() == SUBJECT_TYPE_MANDATORY) {
                System.out.print(j + "." + subject.getSubjectName() + " ");
                j++;
            }
        }
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType() == SUBJECT_TYPE_CHOICE) {
                System.out.print(j + "." + subject.getSubjectName() + " ");
                j++;
            }
        }
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호 점수를 만들 때 과목이 필요한게 아닌가? 으앙거ㅏ

        String addSubject = "";

        System.out.println("등록할 과목을 선택해 주세요.");
        int j = 1;
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType() == SUBJECT_TYPE_MANDATORY) {
                System.out.print(j + "." + subject.getSubjectName() + " ");
                j++;
            }
        }
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType() == SUBJECT_TYPE_CHOICE) {
                System.out.print(j + "." + subject.getSubjectName() + " ");
                j++;
            }
        }

        int input = sc.nextInt();
        switch (input) {
            case 1:
                System.out.println("Java 과목 성적을 불러오는 중입니다.");
                addSubject = "1";
                break;

            case 2:
                System.out.println("객체지향 과목 성적을 불러오는 중입니다.");
                addSubject = "2";
                break;
            case 3:
                System.out.println("Spring 과목 성적을 불러오는 중입니다.");
                addSubject = "3";
                break;
            case 4:
                System.out.println("JPA 과목 성적을 불러오는 중입니다.");
                addSubject = "4";
                break;
            case 5:
                System.out.println("MySQL 과목 성적을 불러오는 중입니다.");
                addSubject = "5";
                break;
            case 6:
                System.out.println("디자인 패턴 과목 성적을 불러오는 중입니다.");
                addSubject = "6";
                break;
            case 7:
                System.out.println("Spring Seucrity 과목 성적을 불러오는 중입니다.");
                addSubject = "7";
                break;
            case 8:
                System.out.println("Redis 과목 성적을 불러오는 중입니다.");
                addSubject = "8";
                break;
            case 9:
                System.out.println("MongoDB 과목 성적을 불러오는 중입니다.");
                addSubject = "9";
                break;
        }

        System.out.println("시험 점수를 입력해 주십시오.");
        String score = sc.next();

        String Grade;

        if (Integer.parseInt(score) >= 95) Grade = "A";
        else if (Integer.parseInt(score) >= 90) Grade = "B";
        else if (Integer.parseInt(score) >= 80) Grade = "C";
        else if (Integer.parseInt(score) >= 70) Grade = "D";
        else if (Integer.parseInt(score) >= 60) Grade = "F";
        else Grade = "N";

        System.out.println("시험 점수를 등록합니다...");
        Score sco = new Score(score, addSubject, studentId, Grade);
        scoreStore.add(sco);
        System.out.println("\n점수 등록 성공!"); //점수 등록을 그냥 하면 과목을 어디서 저장
    } //성적 등록할 때 과목을

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() { //돼따!
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String subject = getSubject();

        // 학생번호 studentId에 넣고 과목번호 subject에 넣음
        // 요거에 해당하는 데이터만 불러오기.

        int i = 1;

//        이거 studentId랑 subject에 따른 다른 정보를 불러와야함
        for (Score score : scoreStore) {
            if (i >= 10) break;
            if (Objects.equals(score.getStudentId(), studentId) && Objects.equals(score.getSubjectName(), subject)) // 학생번호랑 과목 번호가 같으면
            {
                System.out.println(i + "회차");
                i++;
                if (Integer.parseInt(score.getScoreId()) >= 95) {
                    System.out.println("등급 : " + score.getGrade());
                } else if (Integer.parseInt(score.getScoreId()) >= 90) {
                    System.out.println("등급 : " + score.getGrade());
                } else if (Integer.parseInt(score.getScoreId()) >= 80) {
                    System.out.println("등급 : " + score.getGrade());
                } else if (Integer.parseInt(score.getScoreId()) >= 70) { //돼따 !
                    System.out.println("등급 : " + score.getGrade());
                } else if (Integer.parseInt(score.getScoreId()) >= 60) {
                    System.out.println("등급 : " + score.getGrade());
                } else System.out.println("등급 : " + score.getGrade());
            }
        }
        System.out.println("\n등급 조회 성공!");
    }

}