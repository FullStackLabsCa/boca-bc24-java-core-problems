package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        String input = scanner.nextLine();

        if (!isValidInput(input)) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            return;
        }

        int n = Integer.parseInt(input);
        List<Integer> fibonacciSeries = generateFibonacci(n);
        System.out.println("problems.Fibonacci Series up to " + n + " numbers: " + fibonacciSeries);
    }

    private static boolean isValidInput(String input) {
        try {
            int num = Integer.parseInt(input);
            return num >= 4 && num <= 47;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<Integer> generateFibonacci(int n) {
        List<Integer> fibonacciSeries = new ArrayList<>();
        if (n <= 0) return fibonacciSeries;

        fibonacciSeries.add(0);  // First number of the Fibonacci series
        if (n == 1) return fibonacciSeries;

        fibonacciSeries.add(1);  // Second number of the Fibonacci series

        for (int i = 2; i < n; i++) {
            int nextFibonacciNumber = fibonacciSeries.get(i - 1) + fibonacciSeries.get(i - 2);
            fibonacciSeries.add(nextFibonacciNumber);
        }

        return fibonacciSeries;
    }
    // testing
}
