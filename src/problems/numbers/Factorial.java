package problems.numbers;

import java.util.Scanner;

public class Factorial {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Factorial of: ");
        int fact = Integer.parseInt(scanner.nextLine());
        int sum = 1;
        int  i;
        for (i = 1; i<=fact; i++){
            sum = sum *  i;
        }
        System.out.println("Factorial of " + fact + " is: " + sum);
    }
}
