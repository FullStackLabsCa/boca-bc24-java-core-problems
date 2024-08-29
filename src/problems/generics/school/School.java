package generics.school;

import java.util.*;

public class School<S, G extends Number> {

    private final Map<String, Map<S, G>> schoolToCourse;

    private static final List<String> commandLists = Arrays.asList("add_course", "remove_course", "list_courses", "enroll_student", "assign_grade", "list_grades",
            "report_unique_courses", "report_unique_students", "report_average_score", "report_cumulative_average", "print_map", "x", "X");

    public School() {
        this.schoolToCourse = new HashMap<>();
    }

    // Add course
    private void addCourse(String course) {
        if (!schoolToCourse.containsKey(course)) {
            schoolToCourse.putIfAbsent(course, new HashMap<>());
            System.out.println("Course '" + course + "' added.\n");
        } else {
            System.out.println("Course '" + course + "' already exists.\n");
        }
    }

    // Remove given course
    private void removeCourse(String course) {
        if (schoolToCourse.containsKey(course)) {
            schoolToCourse.remove(course);
        } else {
            System.out.println("Student with this identifier doesn't exist in a map.!");
        }
    }

    // Get list of unique courses
    private void getCourseList() {
        ArrayList<String> courseList = new ArrayList<>();
        for (Map.Entry<String, Map<S, G>> courseKeyList: schoolToCourse.entrySet()) {
            courseList.add(courseKeyList.getKey());
        }
        System.out.println("Courses offered:\n" + courseList);
    }

    // Enroll given student to given course
    private void enrollStudent(String course, S studentIdentifier) {
        if (schoolToCourse.containsKey(course) && schoolToCourse.get(course) != null) {
            schoolToCourse.get(course).put(studentIdentifier, null);
            System.out.println("enroll_student " + course + " " + studentIdentifier);
            System.out.println("Student '" + studentIdentifier + "' enrolled in course '" + course + "'.");
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + course + "' does not exist.");
        }
    }

    // Assign grade to give student for given course
    private void assignGrade(String course, S studentIdentifier, G grade) {
        if (schoolToCourse.get(course).containsKey(studentIdentifier)) {
            schoolToCourse.get(course).put(studentIdentifier, grade);
            System.out.println("Grade '" + grade + "' assigned to student '" + studentIdentifier + "' in course '" + course + "'.\n");
        } else {
            System.out.println("Error: Cannot assign grade. Student '" + studentIdentifier + "' is not enrolled in course '" + course + "'.");
        }
    }

    // List grades of student for given course
    private void listGrades(String course) {
        if (schoolToCourse.containsKey(course)) {
            System.out.println("Grades for course '" + course + "':");
            for(Map.Entry<S, G> studentGradeEntry: schoolToCourse.get(course).entrySet()) {
                System.out.println("Student: " + studentGradeEntry.getKey() + ", Grade: " + studentGradeEntry.getValue());
            }
        } else {
            System.out.println("Error: Course '" + course + "' does not exist.");
        }
    }

    // List unique students across all courses
    private void reportUniqueStudents() {
        Set<S> students = new HashSet<>();
        for (Map<S, G> studentGrade: schoolToCourse.values()) {
            students.addAll(studentGrade.keySet());
        }
        System.out.println("Unique students enrolled:\n" + students);
    }

    // Calculate average score of given course
    private void reportAverageScore(String course) {
        Map<S, G> studentGrades = schoolToCourse.get(course);
        if (studentGrades == null || studentGrades.isEmpty()) {
            System.out.println("No student entrolled in the course '" + course +"'.");
        }
        double sum = 0.0;
        for (G grade : studentGrades.values()) {
            sum += grade.doubleValue();
        }

        System.out.println("Average score for course '" + course + "': " + sum / studentGrades.size() + "'");
    }

    // Calculate CGPA of given student across all course
    private void reportCumulativeAverage(S studentIdentifier) {
        double sum = 0.0, studentEnrollmentCount = 0;
        for(Map<S, G> studentGrade : schoolToCourse.values()) {
            if (studentGrade.containsKey(studentIdentifier)) {
                studentEnrollmentCount++;
                sum += studentGrade.get(studentIdentifier).doubleValue();
            }
        }
        System.out.println("Cumulative average score for student '" + studentIdentifier + "': " + (sum / studentEnrollmentCount));
    }

    // Print map
    public void printMap() {
        System.out.println("\nstudentToGrade===\n" + schoolToCourse);
    }

    // Check whether string is valid or not
    private static boolean isValidString(String inputStr) {
        if (inputStr.isEmpty()) {
            return false;
        }
        String [] inputs = inputStr.split(" ");
        if (inputs.length > 1  && !commandLists.contains(inputs[0])) {
            return false;
        }
        return true;
    }

    // Process command as per user input
    public void processCommand(String userInputString) {
        String [] inputs = userInputString.trim().split(" ");
        switch (inputs[0]) {
            case "add_course":
                this.addCourse(inputs[1]);
                break;
            case "remove_course":
                this.removeCourse(inputs[1]);
                break;
            case "list_courses":
                this.getCourseList();
                break;
            case "enroll_student":
                this.enrollStudent(inputs[1], (S) inputs[2]);
                break;
            case "assign_grade":
                System.out.println("instance of inputs[3]-==" + inputs[3] instanceof String);
                this.assignGrade(inputs[1], (S) inputs[2], (G) Double.valueOf(inputs[3]));
                break;
            case "list_grades":
                this.listGrades(inputs[1]);
                break;
            case "report_unique_courses":
                this.getCourseList();
                break;
            case "report_unique_students":
                this.reportUniqueStudents();
                break;
            case "report_average_score":
                this.reportAverageScore(inputs[1]);
                break;
            case "report_cumulative_average":
                this.reportCumulativeAverage((S) inputs[1]);
                break;
            case "print_map":
                this.printMap();
            case "unknown_command":
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInputString;
//        Course<String, Integer> course = new Course<>();
        School<Integer, Double> school = new School<>();
        do {
            System.out.println("Enter a command from given list:\n");
            for (int i = 1; i <= commandLists.size(); i++) {
                System.out.println(i + ". " + commandLists.get(i - 1));
            }
            userInputString = scanner.nextLine().trim();

            Boolean isValidInput = isValidString(userInputString);

            if (!isValidInput) {
                System.out.println("Please enter a valid string");
                return;
            }
            school.processCommand(userInputString);

        } while (!userInputString.equalsIgnoreCase("x"));
    }
}
