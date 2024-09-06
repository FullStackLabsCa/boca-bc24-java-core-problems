package genproblem.schoolbook;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class School<S, G extends Number> {
    private final Gradebook<S, G> gradebook;
    private final Scanner scanner;

    // Constructor
    public School() {
        this.gradebook = new Gradebook<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while(true){
            System.out.println("Choose an action:");
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Update Grade");
            System.out.println("5. List Courses");
            System.out.println("6. View Grades");
            System.out.println("7. Report Unique Courses");
            System.out.println("8. Report Unique Students");
            System.out.println("9. Report Average Score");
            System.out.println("10. Report Cumulative Average");
            System.out.println("11. Exit");

            String command = scanner.nextLine();
            processCommand(command);
        }
    }

    //process command methods to deal with input expression for other ,methods
    public void processCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 0) {
            System.out.println("Error: Command cannot be empty.");
            return;
        }

        String action = parts[0];
        switch (action) {
            case "add_course":
                if (parts.length < 2) {
                    System.out.println("Error: Missing course name.");
                } else {
                    addCourse(parts[1]);
                }
                break;
            case "enroll_student":
                if (parts.length < 3) {
                    System.out.println("Error: Missing course name or student ID.");
                } else {
                    enrollStudent(parts[1], (S) parts[2]);
                }
                break;
            case "assign_grade":
                if (parts.length < 4) {
                    System.out.println("Error: Missing course name, student ID, or grade.");
                } else {
                    try {
                        G grade = (G) Double.valueOf(parts[3]);
                        assignGrade(parts[1], (S) parts[2], grade);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid grade format. Grade must be a number.");
                    }
                }
                break;
            case "list_grades":
                if (parts.length < 2) {
                    System.out.println("Error: Missing course name.");
                } else {
                    listGrades(parts[1]);
                }
                break;
            case "list_courses":
                listCourses();
                break;
            case "report_unique_courses":
                reportUniqueCourses();
                break;
            case "report_unique_students":
                reportUniqueStudents();
                break;
            case "report_average_score":
                if (parts.length < 2) {
                    System.out.println("Error: Missing course name.");
                } else {
                    reportAverageScore(parts[1]);
                }
                break;
            case "report_cumulative_average":
                if (parts.length < 2) {
                    System.out.println("Error: Missing student ID.");
                } else {
                    reportCumulativeAverage((S) parts[1]);
                }
                break;
            case "exit":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Error: Unknown command '" + action + "'. Please use a valid command.");
        }
    }

    private void addCourse(String courseName) {
        gradebook.addCourse(new CourseBook<>(courseName));
        System.out.println("Course '" + courseName + "' added.");
    }

    private void enrollStudent(String courseName, S studentId) {
        gradebook.addStudentToCourse(courseName, studentId, (G) Double.valueOf(0));
        System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
    }

    private void assignGrade(String courseName, S studentId, G grade) {
        gradebook.updateStudentGrade(courseName, studentId, grade);
        System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
    }

    private void listGrades(String courseName) {
        Map<S, G> grades = gradebook.getAllGradesForCourse(courseName);
        if (grades.isEmpty()) {
            System.out.println("No grades available for course '" + courseName + "'.");
        } else {
            System.out.println("Grades for course '" + courseName + "':");
            for (Map.Entry<S, G> entry : grades.entrySet()) {
                System.out.println("Student: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        }
    }

    private void listCourses() {
        gradebook.listCourses();
    }

    private void reportUniqueCourses() {
        System.out.println("Courses offered:");
        for (String course : gradebook.getCourses()) {
            System.out.println(course);
        }
    }

    private void reportUniqueStudents() {
        Set<S> students = gradebook.getUniqueStudents();
        System.out.println("Unique students enrolled:");
        for (S student : students) {
            System.out.println(student);
        }
    }

    private void reportAverageScore(String courseName) {
        double averageScore = gradebook.calculateAverageScore(courseName);
        System.out.println("Average score for course '" + courseName + "': " + averageScore);
    }

    private void reportCumulativeAverage(S studentId) {
        double cumulativeAverage = gradebook.calculateCumulativeAverage(studentId);
        System.out.println("Cumulative average score for student '" + studentId + "': " + cumulativeAverage);
    }

    public static void main(String[] args) {
        School<String, Double> school = new School<>();
        school.start();
    }
}
