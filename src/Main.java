import java.util.*;

class Main {
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "";
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
            System.out.println("4. 수강생 상태 변경");
            System.out.println("5. 수강생 이름 변경");
            System.out.println("6. 수강생 상태별 조회");
            System.out.println("7. 수강생 삭제");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                case 4 -> changeStudentStatus();
                case 5 -> changeStudentName();
                case 6 -> inquireStudentStat();
                case 7 -> removeStudent();
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void removeStudent() {
        System.out.println("삭제할 수강생 이름을 입력해주십시오.");
        String studentName = sc.next();
        studentStore.removeIf(student -> student.getStudentName().equals(studentName));
    }

    private static void inquireStudentStat() {
        System.out.println("조회할 상태를 입력해주십시오.");
        String stat = sc.next();
        for (Student student : studentStore) {
            if (student.getStatus().equals(stat)) {
                System.out.println(student.getStudentName() + " : " + stat);
            }
        }
    }

    private static void changeStudentName() {
        System.out.println("이름을 변경할 수강생 이름을 입력해주십시오.");
        String studentName = sc.next();
        System.out.println("변경할 이름을 입력해 주십시오.");
        String changeName = sc.next();
        for (Student student : studentStore) {
            if (student.getStudentName().equals(studentName)) {
                student.changeName(changeName);
            }
        }
    }

    private static void changeStudentStatus() {
        System.out.println("상태를 변경할 수강생 이름을 입력해주십시오.");
        String studentName = sc.next();
        System.out.println("변경할 상태를 입력해 주십시오.");
        String studentStatus = sc.next();
        for (Student student : studentStore) {
            if (student.getStudentName().equals(studentName)) {
                student.changeStatus(studentStatus);
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
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                System.out.print(i + "." + subject.getSubjectName() + " ");
                i++;
            }
        }
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                System.out.print(i + "." + subject.getSubjectName() + " ");
                i++;
            }
        }
        String subName = sc.next();

        System.out.println("학생의 상태를 입력해 주십시오.");
        String stat = sc.next();


        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, subName, stat);


        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // Iterator 써서 studentStore에 저장된 모든 학생들 정보 훑어서 수강생 이름이랑 번호 나오게
        for (Student student : studentStore) {
            System.out.println("수강생 번호 : " + student.getStudentId() + " 이름 : " + student.getStudentName() + " 상태 : " + student.getStatus() + " 선택한 과목명 : " + student.getStudentSubject());
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
            System.out.println("5. 과목별 평균 등급 조회");
            System.out.println("6. 특정 상태 수강생들의 필수 과목 평균 조회");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                case 5 -> inquireAverageGrade();
                case 6 -> inquireAverageGradeByStatus();
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void inquireAverageGradeByStatus() {
        double sum = 0; // 이거 애매한데 ;
        double count = 0;
        String averageGrade;
        System.out.println("조회할 상태를 입력해주십시오.");
        String stat = sc.next();
        for (Student student : studentStore) {
            if (student.getStatus().equals(stat)) {
                System.out.println(student.getStudentName() + " 님의 평균 과목 등급 ");
                for (Score score : scoreStore) {
                    if (score.getStudentId().equals(student.getStudentId())) {
                        //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                        //과목 이름이 Java면 회차별 성적
                        if (score.getSubjectName().equals("Java")) {
                            count++; //회차 대신
                            switch (score.getGrade()) {
                                case "A" -> sum += 10;
                                case "B" -> sum += 9;
                                case "C" -> sum += 8;
                                case "D" -> sum += 7;
                                case "F" -> sum += 6;
                                case "N" -> sum += 5;
                            }
                        }
                    }
                }
                if (sum / count > 9) averageGrade = "A";
                else if (sum / count > 8) averageGrade = "B";
                else if (sum / count > 7) averageGrade = "C";
                else if (sum / count > 6) averageGrade = "D";
                else if (sum / count > 5) averageGrade = "F";
                else averageGrade = "N";
                if (count == 0) System.out.println("Java 성적이 입력되지 않았습니다.");
                else System.out.println("Java 평균 성적 : " + averageGrade);
                sum = 0;
                count = 0;
                for (Score score : scoreStore) {
                    if (score.getStudentId().equals(student.getStudentId())) {
                        //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                        //과목 이름이 Java면 회차별 성적
                        if (score.getSubjectName().equals("객체지향")) {
                            count++; //회차 대신
                            switch (score.getGrade()) {
                                case "A" -> sum += 10;
                                case "B" -> sum += 9;
                                case "C" -> sum += 8;
                                case "D" -> sum += 7;
                                case "F" -> sum += 6;
                                case "N" -> sum += 5;
                            }
                        }
                    }
                }
                if (sum / count > 9) averageGrade = "A";
                else if (sum / count > 8) averageGrade = "B";
                else if (sum / count > 7) averageGrade = "C";
                else if (sum / count > 6) averageGrade = "D";
                else if (sum / count > 5) averageGrade = "F";
                else averageGrade = "N";
                if (count == 0) System.out.println("객체지향 성적이 입력되지 않았습니다.");
                else System.out.println("객체지향 평균 성적 : " + averageGrade);
                sum = 0;
                count = 0;
                for (Score score : scoreStore) {
                    if (score.getStudentId().equals(student.getStudentId())) {
                        //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                        //과목 이름이 Java면 회차별 성적
                        if (score.getSubjectName().equals("Spring")) {
                            count++; //회차 대신
                            switch (score.getGrade()) {
                                case "A" -> sum += 10;
                                case "B" -> sum += 9;
                                case "C" -> sum += 8;
                                case "D" -> sum += 7;
                                case "F" -> sum += 6;
                                case "N" -> sum += 5;
                            }
                        }
                    }
                }
                if (sum / count > 9) averageGrade = "A";
                else if (sum / count > 8) averageGrade = "B";
                else if (sum / count > 7) averageGrade = "C";
                else if (sum / count > 6) averageGrade = "D";
                else if (sum / count > 5) averageGrade = "F";
                else averageGrade = "N";
                if (count == 0) System.out.println("Spring 성적이 입력되지 않았습니다.");
                else System.out.println("Spring 평균 성적 : " + averageGrade);
                sum = 0;
                count = 0;
                for (Score score : scoreStore) {
                    if (score.getStudentId().equals(student.getStudentId())) {
                        //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                        //과목 이름이 Java면 회차별 성적
                        if (score.getSubjectName().equals("JPA")) {
                            count++; //회차 대신
                            switch (score.getGrade()) {
                                case "A" -> sum += 10;
                                case "B" -> sum += 9;
                                case "C" -> sum += 8;
                                case "D" -> sum += 7;
                                case "F" -> sum += 6;
                                case "N" -> sum += 5;
                            }
                        }
                    }
                }
                if (sum / count > 9) averageGrade = "A";
                else if (sum / count > 8) averageGrade = "B";
                else if (sum / count > 7) averageGrade = "C";
                else if (sum / count > 6) averageGrade = "D";
                else if (sum / count > 5) averageGrade = "F";
                else averageGrade = "N";
                if (count == 0) System.out.println("JPA 성적이 입력되지 않았습니다.");
                else System.out.println("JPA 평균 성적 : " + averageGrade);
                sum = 0;
                count = 0;
                for (Score score : scoreStore) {
                    if (score.getStudentId().equals(student.getStudentId())) {
                        //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                        //과목 이름이 Java면 회차별 성적
                        if (score.getSubjectName().equals("MySQL")) {
                            count++; //회차 대신
                            switch (score.getGrade()) {
                                case "A" -> sum += 10;
                                case "B" -> sum += 9;
                                case "C" -> sum += 8;
                                case "D" -> sum += 7;
                                case "F" -> sum += 6;
                                case "N" -> sum += 5;
                            }
                        }
                    }
                }
                if (sum / count > 9) averageGrade = "A";
                else if (sum / count > 8) averageGrade = "B";
                else if (sum / count > 7) averageGrade = "C";
                else if (sum / count > 6) averageGrade = "D";
                else if (sum / count > 5) averageGrade = "F";
                else averageGrade = "N";
                if (count == 0) System.out.println("MySQL 성적이 입력되지 않았습니다.");
                else System.out.println("MySQL 평균 성적 : " + averageGrade);
                sum = 0;
                count = 0;
            }
            System.out.println(" ");
        }
    }

    private static void inquireAverageGrade() {
        double sum = 0; // 이거 애매한데 ;
        double count = 0;
        String studentId = getStudentId();
        System.out.println("과목별 평균 점수를 불러오는 중입니다.");
        String averageGrade;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("Java")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("Java 성적이 입력되지 않았습니다.");
        else System.out.println("Java 평균 성적 : " + averageGrade);

        // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데; 이거 선택한 과목만 보이게 해야되네
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("객체지향")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("객체지향 성적이 입력되지 않았습니다.");
        else System.out.println("객체지향 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("Spring")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("Spring 성적이 입력되지 않았습니다.");
        else System.out.println("Spring 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("JPA")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("JPA 성적이 입력되지 않았습니다.");
        else System.out.println("JPA 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("MySQL")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("MySQL 성적이 입력되지 않았습니다.");
        else System.out.println("MySQL 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("디자인 패턴")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("디자인 패턴 성적이 입력되지 않았습니다.");
        else System.out.println("디자인 패턴 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("Spring Security")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("Spring Security 성적이 입력되지 않았습니다.");
        else System.out.println("Spring Security 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("Redis")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("Redis 성적이 입력되지 않았습니다.");
        else System.out.println("Redis 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
        sum = 0;
        count = 0;
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                //학생 번호랑 같은 점수를 불러와서 보여주면 다 보여줄텐데
                //과목 이름이 Java면 회차별 성적
                if (score.getSubjectName().equals("MongoDB")) {
                    count++; //회차 대신
                    switch (score.getGrade()) {
                        case "A" -> sum += 10;
                        case "B" -> sum += 9;
                        case "C" -> sum += 8;
                        case "D" -> sum += 7;
                        case "F" -> sum += 6;
                        case "N" -> sum += 5;
                    }
                }
            }
        }
        if (sum / count > 9) averageGrade = "A";
        else if (sum / count > 8) averageGrade = "B";
        else if (sum / count > 7) averageGrade = "C";
        else if (sum / count > 6) averageGrade = "D";
        else if (sum / count > 5) averageGrade = "F";
        else averageGrade = "N";
        if (count == 0) System.out.println("MongoDB 성적이 입력되지 않았습니다.");
        else System.out.println("MongoDB 평균 성적 : " + averageGrade); // 이거 깔끔하게 안되나 음. 과목 다하면 진짜 길어지는데;
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    private static String getSubject() {
        System.out.println("불러올 과목을 선택해 주세요.");
        int j = 1;
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                System.out.print(j + "." + subject.getSubjectName() + " ");
                j++;
            }
        }
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
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
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                System.out.print(j + "." + subject.getSubjectName() + " ");
                j++;
            }
        }
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                System.out.print(j + "." + subject.getSubjectName() + " ");
                j++;
            }
        }

        String input = sc.next();
        addSubject = switch (input) {
            case "Java" -> {
                System.out.println("Java 과목 성적을 불러오는 중입니다.");
                yield "Java";
            }
            case "객체지향" -> {
                System.out.println("객체지향 과목 성적을 불러오는 중입니다.");
                yield "객체지향";
            }
            case "Spring" -> {
                System.out.println("Spring 과목 성적을 불러오는 중입니다.");
                yield "Spring";
            }
            case "JPA" -> {
                System.out.println("JPA 과목 성적을 불러오는 중입니다.");
                yield "JPA";
            }
            case "MySQL" -> {
                System.out.println("MySQL 과목 성적을 불러오는 중입니다.");
                yield "MySQL";
            }
            case "디자인 패턴" -> {
                System.out.println("디자인 패턴 과목 성적을 불러오는 중입니다.");
                yield "디자인 패턴";
            }
            case "Spring Security" -> {
                System.out.println("Spring Seucrity 과목 성적을 불러오는 중입니다.");
                yield "Spring Security";
            }
            case "Redis" -> {
                System.out.println("Redis 과목 성적을 불러오는 중입니다.");
                yield "Redis";
            }
            case "MongoDB" -> {
                System.out.println("MongoDB 과목 성적을 불러오는 중입니다.");
                yield "MongoDB";
            }
            default -> addSubject;
        };

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
        if (sco.getTime() <= 10) {
            scoreStore.add(sco);
            System.out.println("\n점수 등록 성공!");
        } else System.out.println("점수 등록 실패, 10회차를 넘어갑니다.");
    } //성적 등록할 때 과목을

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String subjectName = getSubject();
        System.out.println("수정할 회차를 입력해주십시오.");
        int time = sc.nextInt();
        System.out.println("수정할 점수를 입력해주십시오.");
        String sco = sc.next();

        String Grade;

        if (Integer.parseInt(sco) >= 95) Grade = "A";
        else if (Integer.parseInt(sco) >= 90) Grade = "B";
        else if (Integer.parseInt(sco) >= 80) Grade = "C";
        else if (Integer.parseInt(sco) >= 70) Grade = "D";
        else if (Integer.parseInt(sco) >= 60) Grade = "F";
        else Grade = "N";

        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId) && score.getSubjectName().equals(subjectName) && score.getTime() == time) {
                score.changeScore(sco);
                score.changeGrade(Grade);
            }
        }
        System.out.println("시험 점수를 수정합니다...");
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() { //돼따!
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String subject = getSubject();

        int i = 1;

        // 학생번호 studentId에 넣고 과목번호 subject에 넣음
        // 요거에 해당하는 데이터만 불러오기.


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