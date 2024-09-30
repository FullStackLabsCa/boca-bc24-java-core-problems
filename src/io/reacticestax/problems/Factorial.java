package io.reacticestax.problems;

// Calculate the factorial of number n (1 >> n <= 12)
public class Factorial {
    public static void main(String[] args) {
        for (int number = 1; number <= 12; number++) {
            int factorial = 1;
            System.out.println("Calculating the factorial of " + number + ":");

            for (int i = number; i > 0; i--) {
                int previousFactorial = factorial;
                factorial *= i;
                System.out.println(previousFactorial + " times " + i + " is " + factorial + ".");
            }

            System.out.println("So, the factorial of " + number + " is " + factorial + ".");
            System.out.println();
        }
    }
}
