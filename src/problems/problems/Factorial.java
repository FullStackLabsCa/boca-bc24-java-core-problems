package problems.problems;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number for the factorial: ");
        int input = Integer.parseInt(scanner.nextLine());
        if (input >= 1 && input <= 12) {
            int factorial = 1;
            while (input > 0) {
                factorial *= input;
                input -= 1;
            }
            System.out.println(factorial);
        }
        else {
            System.out.println("VAlue is not between 1 to 12 ");
        }
    }
}