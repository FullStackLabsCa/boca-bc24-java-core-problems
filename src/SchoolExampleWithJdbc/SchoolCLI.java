package SchoolExampleWithJdbc;

import java.util.Scanner;

// Command-line interface for enrolling students
public class SchoolCLI {
    private static final School school = new School();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the School Management System");
        System.out.println("add_course");
        System.out.println("enroll_student");
        System.out.println("assign_grade");
        System.out.println("list_courses");
        System.out.println("list_grades");
        System.out.println("report_unique_courses");
        System.out.println("report_unique_students");
        System.out.println("report_average_score");

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
                case "list_courses":
                    school.listCourses();
                    break;
                case "list_grades":
                    school.listGrades();
                    break;
                case "report_unique_courses":
                    school.uniqueCourse();
                    break;
                case "report_unique_students":
                    school.uniqueStudents();
                    break;
                case "report_average_score":
                    school.averageGrades(command[1]);
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
