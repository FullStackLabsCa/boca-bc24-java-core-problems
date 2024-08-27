package problems.core;

import java.util.Scanner;
@SuppressWarnings("java:S106")

public class FactorialNumber {
    public static void main(String[] args) {
        System.out.println("Please enter a number to get factorial number:");
        Scanner value = new Scanner(System.in);
        int num = Integer.parseInt(value.next());
        int factorialValue =1;
        for (int i = 1; i <= num; i++) {
            factorialValue = factorialValue * i;
        }

        System.out.println("Factorial value: "+ factorialValue);
    }
}