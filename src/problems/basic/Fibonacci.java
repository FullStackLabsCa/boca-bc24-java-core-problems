package basic;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number to find fibonacci series: ");
        int n = scanner.nextInt(), num1 = 0, num2 = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(num1 + " ");
            int num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
    }
}
