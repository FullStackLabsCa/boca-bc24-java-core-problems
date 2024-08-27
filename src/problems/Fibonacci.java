package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
//        System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        isValidInput(input);


    }

    private static boolean isValidInput(String input) {

        if (!input.matches("^[0-9]+$")) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            return false;
        }
        int n = Integer.parseInt(input);
        if(n < 4 || n > 47) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            return false;
        }
        generateFibonacci(n);
        return true;
    }

    public static List<Integer> generateFibonacci(int n) {
        List<Integer> fibonacci = new ArrayList<>();
        int first = 0;
        int second = 1;
        fibonacci.add(first);
        fibonacci.add(second);

        int sum = 0;
        for (int i = 2; i < n; i++){
            sum = first + second;
            first = second;
            second = sum;
            fibonacci.add(sum);
        }
        System.out.println("problems.Fibonacci Series up to " + n + " numbers: " + fibonacci );
        return fibonacci;
    }
}
