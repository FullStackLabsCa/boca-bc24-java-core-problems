package problems;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("Enter input 1 to 12");
        Scanner scanner = new Scanner(System.in);
        int input = Integer.valueOf(scanner.nextLine());
        if (input < 1 || input > 12) {
            throw new IllegalArgumentException("Input out of range");
        }
        System.out.println("Factorial of : " + input);
        int result = 1;
        for (int i = 1; i <= input; i++) {
            result = result * i;
        }
        System.out.println("The result : " + result);
    }
}
