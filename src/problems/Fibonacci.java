package problems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("Enter the input value between 1 to 100");
        Scanner scanner = new Scanner(System.in);
        String rawInput = scanner.nextLine();
        if (isValidInput(rawInput)) {
            System.out.println("problems.Fibonacci Series up to 4 numbers: " + generateFibonacci(Integer.valueOf(rawInput)));
        }
    }

    private static boolean isValidInput(String inputStr) {
        int inputNum=Integer.MIN_VALUE;
        try{
            inputNum = Integer.valueOf(inputStr);
        }catch (Exception e){
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }

        boolean result = false;

        if (inputNum < 4 || inputNum > 47) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            return false;
        } else {
            return true;
        }
    }

    public static List<Integer> generateFibonacci(int n) {
        int first = 0, temp = 0;
        int second = 1;
        List<Integer> resultList = new ArrayList<>();
        resultList.add(0);
        resultList.add(1);
        for (int j = 0; j < n - 2; j++) {
            temp = first + second;
            first = second;
            second = temp;
            resultList.add(temp);
        }
        return resultList;
    }
}


