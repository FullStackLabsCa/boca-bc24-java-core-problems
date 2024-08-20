package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static List<Integer> generateFibonacci(int num) {
        List<Integer> list = new ArrayList<>();
            if (num <= 47 && num > 4) {
                int a = 0, b = 1, c;
                for (int i = 0; i <= num-1; i++) {
                    list.add(a);
                    c = a + b;
                    b = a;
                    a = c;
                }
            } else if (num == 4) {
                System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
            } else System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        return list;
    }
    public static void main(String[] args) {
        System.out.println("Welcome\nEnter the number of fib in fib series");
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        if (n.matches("\\d")){
        int fib = Integer.parseInt(n);
            {
                List<Integer> list = new ArrayList<>();
                Fibonacci.generateFibonacci(fib);
            }
        }else System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
    }
}

