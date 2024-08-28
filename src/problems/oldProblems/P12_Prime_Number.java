package problems.oldProblems;

import java.util.Scanner;

public class P12_Prime_Number {
    public static void main(String[] args) {

        // Accept input
        // Validate input
        // Business logic
        // Report output

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Starting Number => ");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter Ending Number => ");
        int lastNumber = scanner.nextInt();

        // validate input
        boolean isPrime;
        for (int i = firstNumber; i <= lastNumber; i++) {
            isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
//                    System.out.println(i + " Not prime");
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i + " is PRIME.");
            }
        }
    }

}
