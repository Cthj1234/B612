import java.util.*;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
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

    // index 자동 증가
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

    private static void displayMainView() throws InterruptedException {
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
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
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
        // 기능 구현 (필수 과목, 선택 과목)
        sc.nextLine();
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        addSubjectList(student);
        // 기능 구현
        studentStore.add(student);
        System.out.println("수강생 등록 성공!");
    }

    public static void addSubjectList(Student student) {
        addMandatorySubjects(student);
        addChoiceSubjects(student);
    }

    public static void addMandatorySubjects(Student student) {
        System.out.println("=========================");
        System.out.println("수강할 필수 과목을 3개 이상 선택해주세요");
        addSubjects(student, SUBJECT_TYPE_MANDATORY, 3);
    }

    public static void addChoiceSubjects(Student student) {
        System.out.println("=========================");
        System.out.println("수강할 선택 과목을 2개 이상 선택해주세요");
        addSubjects(student, SUBJECT_TYPE_CHOICE, 2);
    }

    public static void addSubjects(Student student, String subjectType, int minNum) {
        boolean addFlag;
        HashMap<String, Subject> subjectMap;

        do {
            addFlag = true;
            subjectMap = new HashMap<>();
            printSubjects(subjectType);
            System.out.println("=========================");
            System.out.println("과목 번호를 입력하세요 (숫자로 입력)");
            String mandatorySubject = sc.nextLine();
            // 공백 또는 쉼표로 구분받기
            String[] subjectArr = mandatorySubject.split("[, ]");
            if (subjectArr.length < minNum) {
                System.out.println(subjectType + " 과목은 " + minNum + "개 이상 선택해야 합니다. ");
                addFlag = false;
            } else {
                // 입력한 값 중 하나라도 과목 목록에 존재하지 않으면 다시 입력 받아야 함
                for (String subjectId : subjectArr) {
                    subjectId = INDEX_TYPE_SUBJECT + subjectId;
                    for (Subject sub : subjectStore) {
                        // 입력한 과목명이 등록된 과목 번호와 일치할 때
                        if (subjectId.equals(sub.getSubjectId()) && sub.getSubjectType().equals(subjectType)) {
                            subjectMap.put(sub.getSubjectId(), sub);
                            addFlag = true;
                            break;
                        }
                        addFlag = false;
                    }
                    if (!addFlag) {
                        System.out.println("올바른 번호를 입력해주세요!");
                        break;
                    }
                }
            }
        } while (!addFlag);

        student.getSubjectList().putAll(subjectMap);

        // 해당 학생의 subjectList를 설정하는 for문입니다.
        // 해당하는 과목의 이름과, 10개의 배열로 저장합니다.
        // 여기서 -1로 채워두는 이유는 중복 등록 방지입니다 ex) 디자인패턴 3회차에 0점으로 등록 되어있으면 수정 외엔 다시 등록 불가능

        for (String subject : student.getSubjectList().keySet()) {
            int[] arr = new int[10];
            Arrays.fill(arr, -1);
            student.getScoreList().put(subject, arr);
        }

        System.out.println(subjectType + " 과목 등록 완료!");
    }

    public static void printSubjects(String subjectType) {
        System.out.println("=========================");
        if (subjectType.equals(SUBJECT_TYPE_MANDATORY)) {
            System.out.print("[필수] ");
            for (Subject sub : subjectStore) {
                if (sub.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                    System.out.print(sub.getSubjectId().substring(2) + ". " + sub.getSubjectName() + " | ");
                }
            }
        } else {
            System.out.print("[선택] ");
            for (Subject sub : subjectStore) {
                if (sub.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                    System.out.print(sub.getSubjectId().substring(2) + ". " + sub.getSubjectName() + " | ");
                }
            }
        }
        System.out.println();
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        for (Student students : studentStore) {
            System.out.print("[" + students.getStudentId() + "] ");
            System.out.println("이름 : " + students.getStudentName());
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

    // 수강생 번호 입력 받은 후 수강생이 존재하는지 확인
    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String tmp = sc.next();

        for (Student student : studentStore) {
            if (student.getStudentId().equals(tmp)) {
                return tmp;
            }
        }

        return "Invalid";

    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        Student student = null;

        //수정 할 과목의 번호
        int subject_Num;

        // 입력받은 과목의 이름. (scoreList에서 key값으로 사용)
        String subject_Name = "";

        while (studentId.equals("Invalid")) {
            System.out.println("해당 학생은 존재 하지 않습니다.");
            studentId = getStudentId();
        }

        // 해당 학생의 객체 student에 저장
        for (Student tmp_student : studentStore) {
            if (tmp_student.getStudentId().equals(studentId)) {
                student = tmp_student;
                break;
            }
        }

        while (true) {
            int index = 1;

            // 숫자 입력 판별 하는 boolean 입니다.
            boolean check = true;


            String line = "";

            System.out.println("점수 등록 하실 과목의 숫자를 입력 하세요.");

            // 과목 번호, 과목 이름 출력
            for (Subject sub : subjectStore) {
                System.out.println(index++ + ". " + sub.getSubjectName());
            }

            line = sc.next();
            // 숫자 판별(숫자만 입력 가능)
            for (int i = 0; i < line.length(); i++) {
                if (!Character.isDigit(line.charAt(i))) {
                    check = false;
                    break;
                }
            }

            if (!check) {
                System.out.println("숫자만 입력 가능 합니다.");
                continue;
            }

            subject_Num = Integer.parseInt(line);

            // 1 ~ 9 까지만 입력 받고 list는 0 ~ 8 까지만 유효하다.
            if (subject_Num < 1 || subject_Num > 9) {
                System.out.println("유효하지 않은 숫자를 입력 하셨습니다.");
                continue;
            }
            
            // 수정한 예외처리 부분
            try{
                subject_Name = subjectStore.get(subject_Num - 1).getSubjectName();
                if (!Objects.isNull(student) &&
                        student.getSubjectList().get(INDEX_TYPE_SUBJECT + subject_Num).getSubjectName().equals(subject_Name)) break;
            }catch (Exception e){
                System.out.println("해당 학생은 입력하신 과목을 수강하지 않습니다.");
            }


        }

        while (true) {
            System.out.println("등록 하실 회차(1 ~ 10)를 입력 해주세요.");
            int num = sc.nextInt();
            if (num < 1 || num > 10) {
                System.out.println("범위에 벗어난 숫자를 입력 하셨습니다.");
                continue;
            }

            System.out.println(num + "회차의 시험 점수를 입력해주세요.");
            int score_tmp = sc.nextInt();
            if (score_tmp < 0 || score_tmp > 100) {
                System.out.println("시험 점수의 범위를 벗어나셨습니다.");
                continue;
            }

            // ★★ 입력받은 과목의 점수가 저장 되어있는 배열
            int[] arr = student.getScoreList().get(INDEX_TYPE_SUBJECT + subject_Num);
            if (arr[num - 1] != -1) {
                System.out.println("중복 등록은 불가능합니다. 이미 " + num + "회차에 점수가 등록 되어 있습니다.");
                continue;
            }

            arr[num - 1] = score_tmp;
            //수정 완료
            student.getScoreList().put(subject_Name, arr);
            break;
        }

        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        int index = 1;
        int find_Sub_Num = 0;
        Student student = null;
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        while (studentId.equals("Invalid")) {
            System.out.println("해당 학생은 존재 하지 않습니다.");
            studentId = getStudentId();
        }

        // 해당 학생의 객체 student에 저장
        for (Student tmp_student : studentStore) {
            if (tmp_student.getStudentId().equals(studentId)) {
                student = tmp_student;
                break;
            }
        }

        while(true){
            // 기능 구현 (조회할 특정 과목)
            index = 1;
            System.out.println("조회 하실 과목을 입력 해주세요 : ");
            // 과목 번호, 과목 이름 출력
            for (Subject sub : subjectStore) {
                System.out.println(index++ + ". " + sub.getSubjectName());
            }


            //예외 처리 (알맞은 값만 입력 받도록)
            try{
                String input = sc.next();
                find_Sub_Num = Integer.parseInt(input);
                if(find_Sub_Num < 1 || find_Sub_Num > 9 ){
                    System.out.println("해당 과목 번호는 유효하지 않습니다.");
                    continue;
                }
            }catch (NumberFormatException e){
                System.out.println("숫자를 입력 해주시길 바랍니다.");
                continue;
            }

            System.out.println("회차별 등급을 조회합니다...");
            if(find_Sub_Num <= 5) displayGrade_Mandatory(student,find_Sub_Num);
            else displayGrade_Choice(student, find_Sub_Num);

            System.out.println("\n등급 조회 성공!");

            break;
        }

    }

    
    // 해당 학생의 필수 과목 점수 조회
    private static void displayGrade_Mandatory(Student student, int findSubNum) {
        int[] arr = student.getScoreList().get(INDEX_TYPE_SUBJECT + findSubNum);

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == -1) continue;
            System.out.println(i + 1 + "회차 과목 점수 : " + change_Grade_Mandatory(arr[i]));
        }
    }

    // 필수 과목 등급 반환
    private static char change_Grade_Mandatory(int num){
        if(num >= 95) return 'A';
        else if(num >= 90) return 'B';
        else if(num >= 80) return 'C';
        else if(num >= 70) return 'D';
        else if(num >= 60) return 'F';
        else return 'N';
    }


    // 해당 학생의 선택 과목 점수 조회
    private static void displayGrade_Choice(Student student, int findSubNum) {
        int[] arr = student.getScoreList().get(INDEX_TYPE_SUBJECT + findSubNum);

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == -1) continue;
            System.out.println(i + 1 + "회차 과목 점수 : " + change_Grade_Choice(arr[i]));
        }
    }

    // 선택 과목 등급 반환
    private static char change_Grade_Choice(int num) {
        if(num >= 90) return 'A';
        else if(num >= 80) return 'B';
        else if(num >= 70) return 'C';
        else if(num >= 60) return 'D';
        else if(num >= 50) return 'F';
        else return 'N';
    }

}