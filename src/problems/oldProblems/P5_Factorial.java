package problems.oldProblems;

import java.util.Scanner;

public class P5_Factorial {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n;
        boolean validInput = false;

        while (!validInput) {

            System.out.println("Please enter a number between 1 and 12: ");
            if (scanner.hasNext()) {
                n = scanner.nextInt();
                if (n >= 1 && n <= 12) {
                    int factorial = 1;
                    for (int i = 1; i <= n; i++) {
                        factorial *= i;
                    }
                    System.out.println("Factorial of " + n + " is: " + factorial);
                    validInput = true;
                }
            }

        }
        scanner.close();
    }
}
