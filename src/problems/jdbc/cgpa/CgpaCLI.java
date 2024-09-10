package problems.jdbc.cgpa;

import java.util.Scanner;

public class CgpaCLI {
    static Cgpa cgpa= new Cgpa();

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        System.out.print("> ");
        String input = scanner.nextLine();
        String[] command = input.split(" ");

        switch (command[0]) {
            case "get_Cgpa":
                cgpa.getCGPA(Integer.parseInt(command[1]));
                break;
            default:
                System.out.println("Unknown command");
        }
    }
}
