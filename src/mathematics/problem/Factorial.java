package mathematics.problem;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any number to check the factorial");
        int number = scanner.nextInt();
        long factorialNumber = 1;
        for (int i = 1; i <= number; i++) {
            factorialNumber *= i;
        }
        System.out.println(factorialNumber);
    }
}
