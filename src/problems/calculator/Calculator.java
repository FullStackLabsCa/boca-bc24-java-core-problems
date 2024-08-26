package calculator;

import java.util.Scanner;

public class Calculator {
    static boolean isValidString(String expression) {
        boolean isValid = false;
        if (expression.matches("^\\d+(\\.\\d+)?\\s[+\\-*/]\\s\\d+(\\.\\d+)?$")) {
            isValid = true;
        }
        return isValid;
    }

    static double calculate(String expression) {
        double result = 0.0;
        return result;

//        String [] operands = expression.split("[+\\-*/]");

//        switch (operator) {
//            case '+':
//                Addition addition = new Addition(number1, number2);
//                result = addition.getAddition();
//                break;
//            case '-':
//                Subtraction subtraction = new Subtraction(number1, number2);
//                result = subtraction.getSubtraction();
//                break;
//            case '*':
//                Multiplication multiplication = new Multiplication(number1, number2);
//                result = multiplication.getMultiplication();
//                break;
//            case '/':
//                boolean isDivideByZero = divideByZero(number2);
//                if (isDivideByZero) {
//                    System.out.println("Divide by zero exception");
//                    break;
//                }
//                Division division = new Division(number1, number2);
//                result = division.getDivision();
//                break;
//            default:
//                System.out.println("Invalid Operation");
//                return;
//        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter an expression:: ");
        String expression = scanner.nextLine();

        boolean isValidString = isValidString(expression.trim());

        System.out.printf("Is valid string ===" + isValidString);

//        double result = calculate(expression);
//        System.out.printf("Result===" + result);
    }
}
