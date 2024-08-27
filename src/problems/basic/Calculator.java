package basic;

import java.util.Scanner;

public class Calculator {

    //Add two numbers
    public static double performAddition(Double num1, Double num2) {
        return num1 + num2;
    }

    //Subtract two numbers
    public static double performSubtraction(Double num1, Double num2) {
        return num1 - num2;
    }

    //Multiply two numbers
    public static double performMultiplication(Double num1, Double num2) {
        return num1 * num2;
    }

    //divide two numbers
    public static double performDivision(Double num1, Double num2) {
        if (num2 == 0) {
            System.out.println("Can not divide by 0");
            return 0;
        }
        return num1 / num2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number 1: ");
        double num1 = scanner.nextDouble();

        System.out.println("Enter Number 2: ");
        double num2 = scanner.nextDouble();

        System.out.println("Enter operation you want to perform, select anyone from here [+, -, *, /]: ");
        String operation = scanner.next();
        double result;
        switch (operation) {
            case "+":
                result = performAddition(num1, num2);
                break;
            case "-":
                result = performSubtraction(num1, num2);
                break;
            case "*":
                result = performMultiplication(num1, num2);
                break;
            case "/":
                result = performDivision(num1, num2);
                break;
            default:
                System.out.println("Invalid Operation");
                return;
        }

        System.out.println("Your result is for " + num1 + " " + operation + " " + num2 + " = " + result);
    }
}

//Note: if I want to return from divide by zero exception;