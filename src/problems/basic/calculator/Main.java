package basic.calculator;

import basic.calculator.Addition;
import basic.calculator.Division;
import basic.calculator.Multiplication;
import basic.calculator.Subtraction;


import java.util.Scanner;

public class Main {
    public static boolean divideByZero(double num) {
        return num == 0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = "";
        double result = 0.0;
        do {
            System.out.println("Enter a calculator operation you want to perform in this format(10+20): ");
            operation = scanner.nextLine();

            if (operation.matches("^\\d+(\\.\\d+)?[+\\-*/]\\d+(\\.\\d+)?$")) {
                String[] numbers = operation.split("[+\\-*/]");
                char operator = operation.charAt(numbers[0].length());
                double number1 = Double.parseDouble(numbers[0]);
                double number2 = Double.parseDouble(numbers[1]);
                System.out.println("="+ numbers[0] + " = " + numbers[1]);

                switch (operator) {
                    case '+':
                        Addition addition = new Addition(number1, number2);
                        result = addition.getAddition();
                        break;
                    case '-':
                        Subtraction subtraction = new Subtraction(number1, number2);
                        result = subtraction.getSubtraction();
                        break;
                    case '*':
                        Multiplication multiplication = new Multiplication(number1, number2);
                        result = multiplication.getMultiplication();
                        break;
                    case '/':
                        boolean isDivideByZero = divideByZero(number2);
                        if (isDivideByZero) {
                            System.out.println("Divide by zero exception");
                            break;
                        }
                        Division division = new Division(number1, number2);
                        result = division.getDivision();
                        break;
                    default:
                        System.out.println("Invalid Operation");
                        return;
                }
                System.out.println("Your result is for " + number1 + " " + operation + " " + number2 + " = " + result);
            }else if (!operation.equalsIgnoreCase("x")) {
                System.out.println("Please enter a valid input.");
            }
        } while (!operation.equalsIgnoreCase("x"));
    }
}
