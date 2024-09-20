package problems.jdbc;

import problems.jdbc.courses.Course;
import problems.jdbc.enrollments.Enrollment;
import problems.jdbc.grades.Grades;
import problems.jdbc.students.Student;

import java.util.Scanner;

public class MainRunner {

    private static final Course course = new Course();
    private static final Student student = new Student();
    private static final Enrollment enrollment = new Enrollment();
    private static final Grades grades= new Grades();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the School Management System");
        System.out.println("---------------------------------------");

        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("On which you would like to perform operations? ");
            System.out.println("1. Course");
            System.out.println("2. Student");
            System.out.println("3. Enrollment");
            System.out.println("4. Grade");
            System.out.println("5. Exit");

            System.out.println();
            System.out.print("Your input >>>");
            String name = scanner.nextLine();

            switch (name) {
                case "Course":
                    System.out.println("""
                            You can perform 4 operations: \s
                            1. Add a course >> Type 'add_course' \s
                            2. Remove a course >> Type 'remove_course' \s
                            3. Insert  a course >> Type 'update_course' \s
                            4. Get all courses >> Type 'get_course'
                            """);
                    break;
                case "Student":
                    System.out.println("""
                            You can perform 4 operations: \s
                            1. Add a student >> Type 'add_student' \s
                            2. Remove a student >> Type 'remove_student' \s
                            3. Insert  a student >> Type 'update_student' \s
                            4. Get all students >> Type 'get_student'
                            """);
                    break;
                case "Enrollment":
                    System.out.println("""
                            You can perform 3 operations: \s
                            1. Add a enrollment >> Type 'add_enrollment' \s
                            2. Remove a enrollment >> Type 'remove_enrollment' \s
                            3. Insert  a enrollment >> Type 'update_enrollment' \s
                            4. Get all enrollments >> Type 'get_enrollment'
                            """);
                    break;
                case "Grade":
                    System.out.println("""
                            You can perform 3 operation: \s
                            1. Get a enrollment >> Type 'add_grade' \s
                            2. Remove a enrollment >> Type 'remove_grade' \s
                            3. Insert  a enrollment >> Type 'update_grade'
                            """);
                    break;
                case "Exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Unknown Command");
                    flag = false;
            }

            if (flag) {
                System.out.print("Your input >>>");
                String input = scanner.nextLine();
                String[] command = input.split(" ");

                switch (command[0]) {
                    case "add_course" -> course.addCourse(command[1]);
                    case "remove_course" -> course.removeCourse(Integer.parseInt(command[1]));
                    case "update_course" -> course.updateCourse(Integer.parseInt(command[1]), command[2]);
                    case "get_course" -> course.getCourses();
                    case "add_student" -> student.addStudent(command[1]);
                    case "remove_student" -> student.removeStudent(Integer.parseInt(command[1]));
                    case "update_student" ->  student.updateStudent(Integer.parseInt(command[1]), command[2]);
                    case "get_student" -> student.getStudents();
                    case "add_enrollment" ->enrollment.addEnrollment(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                    case "remove_enrollment" -> enrollment.removeEnrollment(Integer.parseInt(command[1]));
                    case "get_enrollment" -> enrollment.getEnrollment();
                    case "add_grade" -> grades.addGrade(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Double.parseDouble(command[3]));
                    case "remove_grade" -> grades.removeGrade(Integer.parseInt(command[1]));
                    case "update_grade" -> grades.updateGrade(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Double.parseDouble(command[3]));
                    default ->
                        System.out.println("Unknown command");
                }
            }
        }
    }
}
