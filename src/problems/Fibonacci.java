package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        boolean isValid = isValidInput(str);

        if(isValid){
            int num = Integer.parseInt(str);
            System.out.println("problems.Fibonacci Series up to " + num + " numbers: " + generateFibonacci(num));
        } else System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
    }

    private static boolean isValidInput(String input) {
        return input.matches("\\d+$") && Integer.parseInt(input) >= 4 && Integer.parseInt(input) <= 47;
    }

    public static List<Integer> generateFibonacci(int n) {
        int first = 0;
        int second = 1;
        List<Integer> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        n -= 2;
        while (n != 0) {
            int sum = first + second;
            list.add(sum);
            n--;
            first = second;
            second = sum;
        }

        return list;
    }
}

