//NOT DONE YET
package problems;

import java.util.Scanner;

public class Calculator {
    public static void clearMemory() {
    }

    public static Object recallAllMemory() {
            return null;
    }


    public static String calculate(String expression) {

        // Remove any spaces around the operators
            expression = expression.replaceAll("\\s", "");

            // Determine the operator used in the expression
            char operator = ' ';
            if (expression.contains("+")) {
                operator = '+';
            } else if (expression.contains("-")) {
                operator = '-';
            } else if (expression.contains("*")) {
                operator = '*';
            } else if (expression.contains("/")) {
                operator = '/';
            } else {
                throw new IllegalArgumentException("Unsupported operation");
            }

            // Split the expression into operands based on the operator
            String[] parts = expression.split("[+-/*]");
            String opr1 = parts[0];
            String opr2 = parts[1];

            // Parse the operands to
            double answerOne = Double.parseDouble(opr1);
            double answerTwo = Double.parseDouble(opr2);

            // Initialize the result
            double result = switch (operator) {
                case '+' -> answerOne + answerTwo;
                case '-' -> answerOne - answerTwo;
                case '*' -> answerOne * answerTwo;
                case '/' -> {
                    if (answerTwo == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    yield answerOne / answerTwo;
                }
                default -> throw new IllegalArgumentException("Unsupported operation");
            };

            // Perform the operation based on the operator

        // Convert the result back to a string and return it
            return String.valueOf(result);

    }

    public static void storeInMemory(double v) {
    }

    public static Object recallMemory() {
            return null;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
       System.out.println("3.5 + 2.1 = " + calculate("3.5 + 2.1"));
       System.out.println("10 - 4 = " + calculate("10 - 4"));
       System.out.println("6 * 7 = " + calculate("6 * 7"));
       System.out.println("8 / 2 = " + calculate("8 / 2"));
    }
}
