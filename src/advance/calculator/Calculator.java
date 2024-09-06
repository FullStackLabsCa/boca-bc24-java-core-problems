package advance.calculator;

import java.util.*;

public class Calculator {

    private static Stack<Double> memoryStack = new Stack<>();
    // we are handling the user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Enter an expression or type 'exit' to quit");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {// call the method to calculate and display the result
                if (input.endsWith("M+")) {
                  String  vamueM =input.substring(0, input.length() - 2).trim(); // Remove M+
                    double result = calculate(vamueM);
                    storeInMemory(result);
                    System.out.println("Stored " + result + " in memory.");
                } else if (input.equalsIgnoreCase("recall")) {
                    System.out.println("Memory: " + recallMemory());
                } else if (input.equalsIgnoreCase("clear")) {
                    clearMemory();
                    System.out.println("Memory cleared.");
                } else if (input.equalsIgnoreCase("recallAll")) {
                    System.out.println("Stored values: " + recallAllMemory());
                } else {
                    double result = calculate(input);
                    System.out.println("Result: " + result);
                }
            } catch (Exception e) {;
            }
        }
        scanner.close();
    }
    //expression cleanup for basic operations
    public static double calculate(String expression) {
        //trim and remove extra spaces
        expression = expression.trim().replaceAll("\\s+", "");
        try {
            if (expression.contains("sqrt")) {
                int startIndex = expression.indexOf("sqrt(") + 5;
                int endIndex = expression.indexOf(")", startIndex);
                double number = Double.parseDouble(expression.substring(startIndex, endIndex));
                if (number < 0) {
                    throw new ArithmeticException("Square root of a negative number is not allowed.");
                }
                return Math.sqrt(number);
            } else if (expression.contains("^")) {
                String[] parts = expression.split("\\^");
                double base = Double.parseDouble(parts[0].trim());
                double exponent = Double.parseDouble(parts[1].trim());
                return Math.pow(base, exponent);
            } else if (expression.contains("+") || expression.contains("-")
                    || expression.contains("*") || expression.contains("/") || expression.contains("sqrt(")) {
                return evaluateExpression(expression);
            }
            return 0.0;
            //throw exception for the invalid expression
        } catch (ArithmeticException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format.");
        }
    }
    //evaluating expression with the operators and brackets
    private static double evaluateExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(sb.toString()));
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                if (operators.isEmpty() || operators.pop() != '(') {
                    throw new IllegalArgumentException("Mismatched parentheses.");
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            }
        }
        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }
        if (values.size() != 1) {
            throw new IllegalStateException("Invalid expression evaluation.");
        }
        return values.pop();//final result
    }

    //helper method  to check precedence level
    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    //perform operation based on the operator
    private static double applyOperation(char op, double value2, double value1) {
        System.out.println("Applying operation: " + value1 + " " + op + " " + value2);
        switch (op) {
            case '+':
                return value1 + value2;
            case '-':
                return value1 - value2;
            case '*':
                return value1 * value2;
            case '/':
                if (value2 == 0) {
                    return Double.NaN;
                }
                return value1 / value2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    //store values and manage memory storage
    // Store values in memory
    public static void storeInMemory(double value) {
        if (memoryStack.size() >= 5) {
            memoryStack.remove(0); // Remove the oldest value (last element)
        }
        memoryStack.push(value);
        System.out.println("value pop"+memoryStack.pop());
    }

    // Clear the memory
    public static Boolean clearMemory() {
        if (memoryStack.isEmpty()) {
            System.out.println("No memory value to clear.");
            return false;
        } else {
            memoryStack.clear();
            System.out.println("Memory cleared.");
            return true;
        }
    }

    // Recall the most recent value from memory
    public static Double recallMemory() {
        if (memoryStack.isEmpty()) {
            System.out.println("No values stored in memory.");
            return null;
        } else {
            return memoryStack.pop();
        }
    }
    // Recall all stored values from memory
    public static String recallAllMemory() {
        if (memoryStack.isEmpty()) {
            return "No values stored in memory.";
        } else {
           String result = "";
            result = "Stored values: " + result + memoryStack.pop() + ", " + memoryStack.toString().replaceAll("(\\[)", "").replaceAll("(\\])", "");
            return (result);
        }
    }
}