package problems.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static List<Integer> generateFibonacci(Integer number) {
        List<Integer> list = new ArrayList<>();
        int num1 = 0;
        int num2 = 1;
        for (int i = 0; i < number; i++) {
            int temp = num1 + num2;
            list.add(num1);
            num1 = num2;
            num2 = temp;
        }
        return list;
    }

    public static boolean isInteger(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) && c != '-') {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (isInteger(input)) {
            int number = Integer.parseInt(input);
            if (number >= 4 && number <= 47) {
                List<Integer> fibonacciSeries = generateFibonacci(number);
                System.out.println("problems.fibonacci.Fibonacci Series up to " + number + " numbers: " + fibonacciSeries);
            } else {
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
    }
}

