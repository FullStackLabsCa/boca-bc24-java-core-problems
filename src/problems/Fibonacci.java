package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Fibonacci numbers to generate (between 4 and 47): ");
        String input = scanner.nextLine();

        try {
            if (input.matches("[a-zA-Z]+")) {
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            } else if (Integer.parseInt(input) > 47 || Integer.parseInt(input) < 4) {
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            } else if (Integer.parseInt(input) == 4) {
                System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
            } else {
//            List<Integer> output = generateFibonacci(Integer.parseInt(input));
//            System.out.println(output);

            }
            List<Integer> output = generateFibonacci(Integer.parseInt(input));
            System.out.println(output);
        }catch(NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
    }
    private static boolean isValidInput(String input) {

        return false;
    }

    public static List<Integer> generateFibonacci(int n) {
        List<Integer> series = new ArrayList<>();
        if (n <= 0) {
            return series;
        }

        int a = 0, b = 1;
        series.add(a);
        if (n > 1) {
            series.add(b);
        }

        for (int i = 2; i < n; i++) {
            int next = a + b;
            series.add(next);
            a = b;
            b = next;
        }

        return series;
    }

    }



