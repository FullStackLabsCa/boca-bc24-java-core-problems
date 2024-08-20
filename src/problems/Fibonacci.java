package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (isValidInput(input)) {
            generateFibonacci(Integer.parseInt(input));
        }
    }

    private static boolean isValidInput(String input) {

        try {
            int integerInput = Integer.parseInt(input);
            if (integerInput < 4 || integerInput > 47) {
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
                return false;
            } else if (integerInput == 4) {
                System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            return false;
        }
    }

        public static List<Integer> generateFibonacci ( int n){

            int firstElement = 0, secondElement = 1, thirdElement;
            List<Integer> fibonacciSeries = new ArrayList<Integer>();
            fibonacciSeries.add(firstElement);
            fibonacciSeries.add(secondElement);
            for (int i = 2; i < n; i++) {
                thirdElement = firstElement + secondElement;
                firstElement = secondElement;
                secondElement = thirdElement;
                fibonacciSeries.add(thirdElement);
            }
            System.out.println(Arrays.toString(fibonacciSeries.toArray()));
            return fibonacciSeries;
        }
    }

