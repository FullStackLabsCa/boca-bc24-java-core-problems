package problems.database_problems.gradebook_db;

import java.util.Scanner;



public class GradeBookLauncher {
    private static final GradeBook gradeBook = new GradeBook();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the GradeBook....");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] command = input.split(" ");
            switch (command[0]) {
                case "add_grades": gradeBook.addGrades(Double.parseDouble(command[1]));
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return;
                default: System.out.println("Unknown command");
            }
        }
    }
}