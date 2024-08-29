package problems.codeproblems;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter value");
        int number = scanner.nextInt();
        int numberOne = 0;
        int numberTwo = 1;
        for(int i = 1; i <= number; i++) {
            System.out.println(numberOne + " ");
            int numberThree = numberOne + numberTwo;
            numberOne = numberTwo;
            numberTwo = numberThree;
        }
    }

}
