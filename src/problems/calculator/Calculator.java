package calculator;

import java.util.Scanner;

public class Calculator {
    public static boolean isValidString(String expression) {
        boolean isValid = false;
        if (expression.matches("^[-+]?(\\d+(\\.\\d+)?|\\.\\d+)([-+*/^%]([-+]?(\\d+(\\.\\d+)?|\\.\\d+)|\\([-+]?(\\d+(\\.\\d+)?|\\.\\d+)([-+*/^%]([-+]?(\\d+(\\.\\d+)?|\\.\\d+)))*\\)))*$")) {
            isValid = true;
        }
        return isValid;
    }

    public static int checkPriority(char operator) {
        int operatorPriority = switch (operator) {
            case '*', '/' -> 1;
            case '+', '-' -> 2;
            case '^' -> 3;
            default -> 0;
        };
        return operatorPriority;
    }

    public static double performOperation (char operator, double operand1, double operand2) throws Exception{
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
                    throw new ArithmeticException("Division by zero is not allowed.");
//                    System.out.println("Division by zero is not allowed.");
//                    break;
                }
                result = operand1 / operand2;
                break;
            default:
                System.out.println("ERROR: INVALID OPERATION");
                break;
        }
        return result;
    }

    public static double calculate(String expression) throws Exception {
        double [] operands = new double[expression.length()];
        char [] operators = new char[expression.length()];
        int operandsIndex = 0, operatorIndex = 0;

        System.out.println("Expression after removing space: " + expression);
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '(') {
                operators[operatorIndex++] = ch;
            } else if (ch == ')') {
                if (operatorIndex > 0 && checkPriority(operators[operatorIndex - 1]) <= checkPriority(ch)) {
                    operandsIndex--;
                    char operator = operators[--operatorIndex];
                    operands[operandsIndex - 1] = performOperation(operator, operands[operandsIndex - 1], operands[operandsIndex]);
                }
                operatorIndex--;
            } else if(Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                operands[operandsIndex] = Double.parseDouble(sb.toString());
                operandsIndex++;
                i--;
            } else if(String.valueOf(ch).matches("[+*/^-]")) {
                if (operatorIndex > 0 && checkPriority(operators[operatorIndex - 1]) <= checkPriority(ch)) {
                    operandsIndex--;
                    char operator = operators[--operatorIndex];
                    operands[operandsIndex - 1] = performOperation(operator, operands[operandsIndex - 1], operands[operandsIndex]);
                }
                operators[operatorIndex++] = ch;
            }
        }

        while (operatorIndex > 0) {
            operandsIndex--;
            char operator = operators[--operatorIndex];
            operands[operandsIndex - 1] = performOperation(operator, operands[operandsIndex - 1], operands[operandsIndex]);
        }

        return operands[0];
    }

    public static void clearMemory() {}

    public static long recallMemory() {
        return 0;
    }

    public static void storeInMemory(double v) {}

    public static Object recallAllMemory() {
        return null;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression:: ");
        String expression = scanner.nextLine();

        boolean isValidString = isValidString(expression.trim());
        if (!isValidString) {
            System.out.println("ERROR: INVALID STRING");
            return;
        }
        System.out.printf("Calculate ===" + calculate(expression.replaceAll(" ", "")));
    }
}
