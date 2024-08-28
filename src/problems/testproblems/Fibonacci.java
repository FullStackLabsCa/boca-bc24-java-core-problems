package testproblems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number to find fibonacci series: ");
        String n = scanner.nextLine();
        boolean isValidNumber = isValidInput(n);
        if (!isValidNumber) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.\"");
            return;
        }
        List<Integer> fibonacciSeries;
        fibonacciSeries = generateFibonacci(Integer.parseInt(n));
        System.out.println("problems.Fibonacci Series up to " + n + " numbers: " + fibonacciSeries + " ");
    }

    private static boolean isValidInput(String input) {
        boolean isValid = true;
        if (!input.matches("^[0-9]+$")) {
            isValid = false;
        } else if (Integer.parseInt(input) < 4 || Integer.parseInt(input) > 47) {
            isValid = false;
        }
        return isValid;
    }

    public static List<Integer> generateFibonacci(int n) {
        int num1 = 0, num2 = 1;
        List<Integer> fibonacci = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            fibonacci.add(num1);
            int num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        return fibonacci;
    }
}

