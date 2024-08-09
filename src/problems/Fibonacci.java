package problems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner obj =new Scanner(System.in);
        System.out.println("Enter the number: ");
        String input  = obj.nextLine();
        try{
            int num = Integer.parseInt(input);
            if (isValidInput(input)){
                System.out.println(generateFibonacci(num));
            }else{
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }




    }

    private static boolean isValidInput(String input) {
            int num = Integer.parseInt(input);
            if (num <4 || num >47 ){
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            }else if (num ==4){
                System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
            }else {
                return true;
            }


        return false;
    }

    public static List<Integer> generateFibonacci(int n) {
        int n1 = 0;
        int n2 = 1;
        int ans;
        List <Integer> fibo = new ArrayList<>();
        fibo.add(n1);
        int count = n;

        while (count > 1) {
            ans = n1 + n2;
            n1 = n2;
            n2 = ans;
            fibo.add(n1);
            count--;

        }
        return fibo;
    }
    }

