package jdbc;

import java.sql.SQLException;
import java.util.Scanner;
import jdbc.School;

// Command-line interface for enrolling students
public class SchoolCLI {
    private static final School school = new School();

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the School Management System");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] command = input.split(" ");

            switch (command[0]) {
                case "add_course":
                    school.addCourse(command[1]);
                    break;
                case "enroll_student":
                    school.enrollStudent(Integer.parseInt(command[2]), command[1], command[3]);
                    break;
                case "assign_grade":
                    school.assignGrade(Integer.parseInt(command[2]), command[1], Double.parseDouble(command[3]));
                    break;
                case "list_grades":
                    school.listGrades(Integer.parseInt(command[1]));
                    break;
                case "list_courses":
                    school.listCourses();
                    break;
                case "report_unique_courses":
                    school.reportUniqueCourses();
                    break;
                case "report_unique_students":
                    school.reportUniqueStudents();
                    break;
                case "report_average_score":
                    school.reportAverageScore(Integer.parseInt(command[1]));
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}