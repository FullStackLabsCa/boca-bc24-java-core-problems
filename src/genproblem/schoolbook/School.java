package genproblem.schoolbook;
import java.util.Map;
import java.util.Scanner;


public class School {

private static Gradebook<String, Integer> gradebook;
private static Scanner scanner;

    public School(){
        gradebook=new Gradebook<>();
        scanner=new Scanner(System.in);
    }
    public static void start() {
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Add CourseBook");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Update Grade");
            System.out.println("5. List Courses");
            System.out.println("6. View Grades");
            System.out.println("7. Exit");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }


            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    enrollStudent();
                    break;
                case 3:
                    assignGrade();
                    break;
                case 4:
                    updateGrade();
                    break;
                case 5:
                    gradebook.listCourses();
                    break;
                case 6:
                    viewGrades();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCourse() {
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        CourseBook<String, Integer> course = new CourseBook<>(courseName);
        gradebook.addCourse(course);
        System.out.println("CourseBook added.");
    }

    private static void enrollStudent() {
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        System.out.println("Enter student ID :");
        String studentId = scanner.nextLine();
        System.out.println("Enter grade:");

        int grade;
        try {
            grade = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (Exception e) {
            System.out.println("Invalid grade. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
            return;
        }


        gradebook.addStudentToCourse(courseName, studentId, grade);
        System.out.println("Student enrolled.");

    }

    private static void assignGrade() {
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine();
        System.out.println("Enter grade:");

        int grade;
        try {
            grade = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (Exception e) {
            System.out.println("Invalid grade. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
            return;
        }
        gradebook.updateStudentGrade(courseName, studentId, grade);
        System.out.println("Grade assigned.");
    }

    private static void updateGrade() {
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine();
        System.out.println("Enter new grade:");

        int newGrade;
        try {
            newGrade = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (Exception e) {
            System.out.println("Invalid grade. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
            return;
        }

        gradebook.updateStudentGrade(courseName, studentId, newGrade);
        System.out.println("Grade updated.");
    }

    private static void viewGrades() {
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        Map<String, Integer> grades = gradebook.getAllGradeForCourse(courseName);
        if (grades.isEmpty()) {
            System.out.println("No grades found for the course or course not found.");
        } else {
            System.out.println("Grades for " + courseName + ":");
            for (Map.Entry<String, Integer> entry : grades.entrySet()) {
                System.out.println("Student ID: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        School school = new School();
        school.start();
    }
}

