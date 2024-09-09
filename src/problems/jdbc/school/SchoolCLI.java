package problems.jdbc.school;

import java.util.Scanner;

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
                case "add_student": //we need to add the student for keeping the track of enrollments as enrollment has the key from student and course
                    school.addStudent(command[1]);
                    break;
                case "enroll_student":  //enroll_student math studentId
                    school.enrollStudent(Integer.parseInt(command[2]), command[1]);
                    break;
                case "assign_grade": //assign_grade courseName studentId grade
                    school.assignGrade(Integer.parseInt(command[2]), command[1], Double.parseDouble(command[3]));
                    break;
                case "list_grades": //
                    school.listGrades();
                    break;
                case "list_allCourses": //
                    school.listAllCourses();
                    break;
                case "list_uniqueStudents": //
                    school.listUniqueStudents();
                    break;
                case "report_averageStudentScore": //
                    school.reportAverageScoreOfStudent(command[1]);
                    break;
                case "report_averageScoreOfCourse": //
                    school.reportAverageScoreOfCourse(command[1]);
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