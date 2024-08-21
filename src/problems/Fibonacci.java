package problems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter String: ");
        String input = scanner.nextLine();
        scanner.close();

        if (!isValidInput(input)) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        } else if (Integer.parseInt(input) < 4 || Integer.parseInt(input) > 47) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        } else if (Integer.parseInt(input) == 4) {
            System.out.println("problems.Fibonacci Series up to 4 numbers: " + generateFibonacci(Integer.parseInt(input)));
        } else {
            generateFibonacci(Integer.parseInt(input));

        }
    }

    private static boolean isValidInput(String input) {
        try {
            return input.matches("-?\\d+");
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Integer> generateFibonacci(int n) {

        int number1 = 0;
        int number2 = 1;
        int temp = 0;
        List<Integer> fibonacciList = new ArrayList();
        fibonacciList.add(number1);
        fibonacciList.add(number2);
        for(int i=0; i< n-2; i++){
            temp = number1 + number2;
            number1 = number2;
            number2 = temp;
            fibonacciList.add(temp);
        }
        System.out.println("Output : "+fibonacciList);

        return fibonacciList;
    }
}

