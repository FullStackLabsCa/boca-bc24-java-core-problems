package problems.generics.schoolbook;

import java.util.*;

public class School<S, G extends Number> {
    private static final String ADD_COURSE = "add_course";
    private static final String ENROLL_STUDENT = "enroll_student";
    private static final String ASSIGN_GRADE = "assign_grade";
    private static final String LIST_GRADES = "list_grades";
    private static final String LIST_COURSES = "list_courses";
    private static final String REPORT_UNIQUE_COURSES = "report_unique_courses";
    private static final String REPORT_UNIQUE_STUDENTS = "report_unique_students";
    private static final String REPORT_AVERAGE_SCORE = "report_average_score";
    private static final String REPORT_CUMULATIVE_AVERAGE = "report_cumulative_average";
    private static final String EXIT = "exit";

    private final List<String> LIST_OF_COMMANDS = Arrays.asList(ADD_COURSE, ENROLL_STUDENT, ASSIGN_GRADE, LIST_GRADES, LIST_COURSES,
            REPORT_UNIQUE_COURSES, REPORT_UNIQUE_STUDENTS, REPORT_AVERAGE_SCORE, REPORT_CUMULATIVE_AVERAGE, EXIT);

    private final Map<String, Course<S, G>> schoolToCourses;

    public School() {
        this.schoolToCourses = new HashMap<>();
    }

    public void addCourse(String courseName) {
        if (!this.schoolToCourses.containsKey(courseName)) {
            this.schoolToCourses.put(courseName, new Course<>());
            System.out.println("Course '" + courseName + "' added.");
        } else System.out.println("Course already added.");
    }

    public void listCourses() {
        System.out.println("Courses offered:");
        Set<String> courses = this.schoolToCourses.keySet();
        for (String course : courses) {
            System.out.println(course);
        }
    }

    public void enrollStudent(String courseName, S student) {
        if (this.schoolToCourses.containsKey(courseName)) {
            Course<S, G> course = this.schoolToCourses.get(courseName);
            course.enrollStudent(student);
            System.out.println("Student '" + student + "' enrolled in course '" + courseName + "'.");
        } else System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
    }

    public void assignGrade(String courseName, S student, G grade) {
        Course<S, G> course = this.schoolToCourses.get(courseName);
        if (course.getStudentToGrade().containsKey(student)) {
            course.assignGrade(student, grade);
            System.out.println("Grade '" + grade + "' assigned to student '" + student + "' in course '" + courseName + "'.");
        } else
            System.out.println("Error: Cannot assign grade. Student '" + student + "' is not enrolled in course '" + courseName + "'.");
    }

    public void listGrades(String courseName) {
        Course<S, G> course = this.schoolToCourses.get(courseName);
        Map<S, G> studentToGrade = course.getStudentToGrade();
        for (Map.Entry<S, G> entry : studentToGrade.entrySet()) {
            System.out.println("Student: " + entry.getKey() + ", Grade: " + entry.getValue());
        }

    }

    public void reportUniqueCourses() {
        Set<String> courses = this.schoolToCourses.keySet();
        System.out.println("Courses offered:");
        for (String course : courses) {
            System.out.println(course);
        }
    }

    public void reportUniqueStudents() {
        Collection<Course<S, G>> students = this.schoolToCourses.values();
        Set<S> studentIdentifiers = new HashSet<>();
        for (Course<S, G> course : students) {
            studentIdentifiers.addAll(course.getStudentToGrade().keySet());
        }
        System.out.println("Unique students enrolled:");
        for (S studentIdentifier : studentIdentifiers) {
            System.out.println(studentIdentifier);
        }
    }

    public void reportAverageScore(String courseName) {
        Collection<G> grades = this.schoolToCourses.get(courseName).getStudentToGrade().values();
        OptionalDouble average = grades.stream().mapToDouble(a -> (double) a).average();
        System.out.println("Average score for course 'Math101': " + String.format("%.1f", average.isPresent() ? average.getAsDouble() : 0));
    }

    public void reportCumulativeAverage(S studentIdentifier) {
        Collection<Course<S, G>> students = this.schoolToCourses.values();
        double total = 0.0;
        int count = 0;
        for (Course<S, G> student : students) {
            if (student.getStudentToGrade().containsKey(studentIdentifier)) {
                G grade = student.getStudentToGrade().get(studentIdentifier);
                total += grade.doubleValue();
                count++;
            }
        }
        System.out.println("Cumulative average score for student '" + studentIdentifier + "': " + String.format("%.1f", total / count));
    }

    public List<String> getLIST_OF_COMMANDS() {
        return LIST_OF_COMMANDS;
    }

    public void processCommand(String commandString) {
        String[] command = commandString.split(" ");
        if (LIST_OF_COMMANDS.contains(command[0])) {
            switch (command[0]) {
                case ADD_COURSE:
                    this.addCourse(command[1]);
                    break;
                case ENROLL_STUDENT:
                    this.enrollStudent(command[1], (S) command[2]);
                    break;
                case ASSIGN_GRADE:
                    this.assignGrade(command[1], (S) command[2], (G) Double.valueOf(command[3]));
                    break;
                case LIST_GRADES:
                    this.listGrades(command[1]);
                    break;
                case LIST_COURSES:
                    this.listCourses();
                    break;
                case REPORT_UNIQUE_COURSES:
                    this.reportUniqueCourses();
                    break;
                case REPORT_UNIQUE_STUDENTS:
                    this.reportUniqueStudents();
                    break;
                case REPORT_AVERAGE_SCORE:
                    this.reportAverageScore(command[1]);
                    break;
                case REPORT_CUMULATIVE_AVERAGE:
                    this.reportCumulativeAverage((S) command[1]);
                    break;
            }
        } else
            System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        School<Integer, Integer> school = new School<>();
        System.out.println("Welcome to Imaginary School.");
        List<String> LIST_OF_COMMANDS = school.getLIST_OF_COMMANDS();
        String command;

        do {
            System.out.println("Please enter a command from the below list:\n");
            for (int i = 1; i <= LIST_OF_COMMANDS.size(); i++) {
                System.out.println(i + ". " + LIST_OF_COMMANDS.get(i - 1));
            }
            command = scanner.nextLine().trim();
            school.processCommand(command);
        } while (!command.equals(LIST_OF_COMMANDS.get(LIST_OF_COMMANDS.size() - 1)));
    }
}
