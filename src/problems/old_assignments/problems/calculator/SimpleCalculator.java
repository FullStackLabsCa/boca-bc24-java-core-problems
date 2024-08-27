package problems.old_assignments.problems.calculator;

import java.util.Scanner;

public class SimpleCalculator  {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Select the operation which you want to perform....");
        System.out.println(" 1. for Addition");
        System.out.println(" 2. for Subtraction");
        System.out.println(" 3. for Multiplication");
        System.out.println(" 4. for Division");
        System.out.println(" 5. for exit ");

        int operationInput = scanner.nextInt();
        if (operationInput != 1 && operationInput != 2 && operationInput != 3
                && operationInput != 4 && operationInput != 5) {
            System.out.println(" You Entered the wrong Input.. Try again");
        } else if (operationInput == 1) {
            System.out.println("Addition Operation is performing");
            System.out.println("Enter the First Number: ");
            double num1 = scanner.nextDouble();
            System.out.println("Enter the Second number: ");
            double num2 = scanner.nextDouble();

            System.out.println("Addition of the two Numbers: " + new problems.old_assignments.problems.problems.Addition().addition(num1,num2) );
        } else if (operationInput == 2) {
            System.out.println("Subtraction Operation is performing");
            System.out.println("Enter the First Number: ");
            double num1 = scanner.nextDouble();
            System.out.println("Enter the Second number: ");
            double num2 = scanner.nextDouble();

            System.out.println("Subtraction of the two Numbers: " + (num1 - num2));
        } else if (operationInput == 3) {
            System.out.println("Multiplication Operation is performing");
            System.out.println("Enter the First Number: ");
            double num1 = scanner.nextDouble();
            System.out.println("Enter the Second number: ");
            double num2 = scanner.nextDouble();

            System.out.println("Multiplication of the two Numbers: " + (num1 * num2));
        } else if (operationInput == 4) {
            System.out.println("Division Operation is performing");
            System.out.println("Enter the First Number: ");
            double num1 = scanner.nextDouble();
            System.out.println("Enter the Second number: ");
            double num2 = scanner.nextDouble();
            if (num2 != 0) {
                System.out.println("Division of the two Numbers: " + (num1 / num2));
            } else System.out.println("***** Any Number Divisible by Zero is Infinity *****");
        }
    }
}
