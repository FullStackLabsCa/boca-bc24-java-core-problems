package problems.codeproblems;

import java.util.Scanner;

public class IndirectSubtraction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number:");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter second number:");
        int secondNumber = scanner.nextInt();

        int subtractionOfNumbers = 0;
        if (firstNumber < 0 || secondNumber < 0) { //subtraction
            System.out.println("Negative numbers are not allowed. Please enter a positive integer.");
            return;
        }
        for(int i = 0; i < secondNumber; i++){
            firstNumber--;
        }
        System.out.println("Subtraction of two numbers is: " + firstNumber);
    }
}
