package basic;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        int fact = 1, number;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number to find a factorial: ");
        number = scanner.nextInt();
        while (number != 1) {
            fact = fact * number;
            number -= 1;
        }
        System.out.println("Factorial of " + number + " is " + fact);
    }
}
