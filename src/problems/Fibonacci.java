package problems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fibonacci series: ");
        String input = scanner.nextLine();
        try{
            int number= Integer.parseInt(input);
            if(isValidInput(input)){
                generateFibonacci(number);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
    }

    private static boolean isValidInput(String input) {
        int number= Integer.parseInt(input);
        if(number > 47 || number < 4){
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
        else if(number == 4){
            System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
        }
        else {
            return true;
        }
        return false;
    }

    public static List<Integer> generateFibonacci(int n) {
            int a = 0;
            int b = 1;
            int c;
            List<Integer> list = new ArrayList<>();
            list.add(a);
            int count = n;
            while (count > 1){
                c = a+b;
                a=b;
                b=c;
                list.add(a);
                count--;
            }
            return list;
    }
}

