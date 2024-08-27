package problems.core;

import java.util.Scanner;
@SuppressWarnings("java:S106")
public class SubtractTwoNumbersWithoutDirectlySubtracting {
    public static void main(String[] args) {
        System.out.println("Please enter a first value: ");
        Scanner value1 = new Scanner(System.in);
        int num1 = value1.nextInt();
        System.out.println("Please enter a second value: ");
        Scanner value2 = new Scanner(System.in);
        int num2 = value2.nextInt();
        int subtract = 0;

        for (int i = 1; i <=num2; i++) {
            subtract = num1 - i;
        }

        System.out.println("Subtract two numbers without directly subtracting it: "+subtract);
    }
}