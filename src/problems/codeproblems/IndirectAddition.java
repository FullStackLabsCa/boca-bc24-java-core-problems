package problems.codeproblems;

import java.util.Scanner;

public class IndirectAddition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number:");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter second number:");
        int secondNumber = scanner.nextInt();

        int additionOfNumbers = 0;
        if(firstNumber < 0 || secondNumber <0 ){
            System.out.println("Negative numbers are not allowed. Please enter a positive integer.");
            return;
        }

        for(int i = 0; i < secondNumber; i++){
            firstNumber++;
        }
        System.out.println("Sum of two numbers is: " + firstNumber);

    }
}
