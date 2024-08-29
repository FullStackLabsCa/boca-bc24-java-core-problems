package calculator;

import java.util.Scanner;

public class Calculator {
    public static boolean isValidString(String expression) {
        boolean isValid = false;
        if (expression.matches("^\\s*\\d+(\\.\\d+)?\\s*[+\\-*/]\\s*\\d+(\\.\\d+)?\\s*$")) {
            isValid = true;
        }
        return isValid;
    }

    public static double performOperation (char operator, double operand1, double operand2) {
        double result = 0;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    System.out.println("Divide by zero exception");
                    break;
                }
                result = operand1 / operand2;
                break;
            default:
                System.out.println("Invalid Operation");
                break;
        }
        return result;
    }

    static double calculate(String expression) {
        double result = 0.0;

        String[] operands = expression.split("[+\\-*/]");
        String operand1 = operands[0];
        String operand2 = operands[1];
        char operator = expression.charAt(operand1.length());
        System.out.println("Operator=" + operand1.length() + " == " + expression.charAt(operand1.length()));
        System.out.println("Operands=" + expression);
        result = performOperation(operator, Double.valueOf(operand1), Double.valueOf(operand2));

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter an expression:: ");
        String expression = scanner.nextLine();

        boolean isValidString = isValidString(expression.trim());
        if (!isValidString) {
            System.out.println("ERROR: INVALID STRING");
            return;
        }
        System.out.printf("Calculate ===" + calculate(expression.trim()));
    }
}
