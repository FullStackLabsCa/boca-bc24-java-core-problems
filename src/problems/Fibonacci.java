package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of Fibonacci numbers to generate (between 4 and 47):");
        System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            return;
        }

        int n = scanner.nextInt();

        if (n < 4 || n > 47) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            return;
        }

        List<Integer> fibonacciSeries = generateFibonacci(n);
        System.out.println("Fibonacci Series up to " + n + " numbers: " + fibonacciSeries);
    }

    public static List<Integer> generateFibonacci(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }

        List<Integer> fibonacciList = new ArrayList<>();
        int firstValue = 0;
        int secondValue = 1;

        for (int i = 0; i < size; i++) {
            fibonacciList.add(firstValue);
            int nextValue = firstValue + secondValue;
            firstValue = secondValue;
            secondValue = nextValue;
        }

        return fibonacciList;
    }
}
