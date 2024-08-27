package problems.prime_number;

import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the starting number: ");
        int start = scanner.nextInt(); // Starting number (inclusive)
        System.out.println("Enter the ending number: ");
        int end = scanner.nextInt();   // Ending number (inclusive)

        System.out.println("Prime numbers between " + start + " and " + end + ":");

        for (int i = start; i <= end; i++) {
            boolean isPrime = true;

            if (i <= 1) {
                isPrime = false; // 0 and 1 are not prime numbers
            } else {
                // Check if i is divisible by any number from 2 to sqrt(i)
                for (int j = 2; j * j <= i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }

            if (isPrime) {
                System.out.print(i + ", "); // Print the prime number
            }
        }
    }
}