package jdbc.school;

import java.util.Scanner;

// Command-line interface for enrolling students
public class SchoolCLI {
    private static final School school = new School();

    public static void main(String[] args) {
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
                case "remove_course":
                    school.removeCourse(command[1]);
                    break;
                case "list_courses":
                    school.getCourseList();
                    break;
                case "enroll_student":
                    school.enrollStudent(Integer.parseInt(command[2]), command[1], command[3]);
                    break;
                case "assign_grade":
                    school.assignGrade(Integer.parseInt(command[2]), command[1], Double.parseDouble(command[3]));
                    break;
                case "list_grades":
                    school.listGrades(command[1]);
                    break;
                case "report_unique_courses":
                    school.getCourseList();
                    break;
                case "report_unique_students":
                    school.reportUniqueStudents();
                    break;
                case "report_average_score":
                    school.reportAverageScore(command[1]);
                    break;
                case "report_cumulative_average":
                    school.reportCumulativeAverage(command[1]);
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}