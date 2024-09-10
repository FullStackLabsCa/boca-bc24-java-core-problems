package problems.jdbc.grades;

import java.util.Scanner;

public class GradesCLI {
    static Grades grades= new Grades();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Course Management System");

        System.out.print("> ");
        String input = scanner.nextLine();
        String[] command = input.split(" ");

        switch (command[0]) {
            case "add_grade":
                grades.addGrade(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Double.parseDouble(command[3]));
                break;
            case "remove_grade":
                grades.removeGrade(Integer.parseInt(command[1]));
                break;
            case "update_grade":
                grades.updateGrade(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Double.parseDouble(command[3]));
                break;
            default:
                System.out.println("Unknown command");
        }
    }
}
