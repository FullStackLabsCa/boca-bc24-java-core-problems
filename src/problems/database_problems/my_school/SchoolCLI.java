package problems.database_problems.my_school;

import java.util.Scanner;

public class SchoolCLI {
    private static final problems.database_problems.my_school.School school = new problems.database_problems.my_school.School();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the My School Management System");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] command = input.split(" ");
            switch (command[0]) {
                case "add_course":
                    School.addCourse(command[1]);
                    break;
                case "enroll_student":
                    School.enrollStudent(Integer.parseInt(command[2]), command[1], command[3]);
                    break;
                case "assign_grade":
                    School.assignGrade(Integer.parseInt(command[2]), command[1], Double.parseDouble(command[3]));
                    break;
                case "average_grade":
                 School.averageGrades(Integer.parseInt(command[1]));
                 break;
                case "cgpa":
                    School.cgpa(Integer.parseInt(command[1]));
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