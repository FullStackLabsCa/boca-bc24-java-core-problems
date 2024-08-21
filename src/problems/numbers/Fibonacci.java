package problems.numbers;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fibonacci series: ");
        String input = scanner.nextLine();

        try {
            int num = Integer.parseInt(input);
            if (isValidInput(input)) {
                generateFibonacci(num);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
    }

    private static boolean isValidInput(String input) {
        int num= Integer.parseInt(input);
        if (num<4 || num>47){
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
        else if (num == 4){
            System.out.println("problems.numbers.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
        }
        else {
            return true;
        }
        return false;
    }

    public static List<Integer> generateFibonacci(int n) {

        int first= 0;
        int second= 1;

        List<Integer> arr = new ArrayList<>();

        arr.add(first);
        arr.add(second);

        for (int i= 2; i<n; i++)
        {
            int next = first + second;
            arr.add(next);
            first = second;
            second = next;
        }
        return arr;
    }
}


