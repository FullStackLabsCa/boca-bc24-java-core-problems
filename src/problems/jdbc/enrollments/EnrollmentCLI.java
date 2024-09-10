package problems.jdbc.enrollments;

import java.util.Scanner;

public class EnrollmentCLI {
    static Enrollment enrollment= new Enrollment();

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Welcome to the Enrollment Management System");

        System.out.print("> ");
        String input = scanner.nextLine();
        String[] command = input.split(" ");

        switch (command[0]) {
            case "add_enrollment":
                enrollment.addEnrollment(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                break;
            case "remove_enrollment":
                enrollment.removeEnrollment(Integer.parseInt(command[1]));
                break;
            case "get_enrollment":
                enrollment.getEnrollment();
                break;
            default:
                System.out.println("Unknown command");
        }
    }
}
