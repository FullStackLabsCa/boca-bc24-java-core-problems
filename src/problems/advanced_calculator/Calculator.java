package problems.advanced_calculator;

import java.util.*;

public class Calculator {
    static List<Double> calculatorMemory = new ArrayList<>();
    static final int MAX_MEMORY = 5;

    public static void main(String[] args) {
        // Example usage
        String input = "sqrt(-9)";
        try {
            System.out.println(calculate(input));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double calculate(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Input is empty or null");
        }

        if (input.contains("M+")) {
            input = input.replaceAll("M\\+", "").trim();
            double result = evaluate(input);
            storeInMemory(result);
            return result;
        } else {
            return evaluate(input);
        }
    }

    private static double evaluate(String input) {
        Deque<Double> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();
        int length = input.length();

        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < length && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    sb.append(input.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop();
            } else if (c == 's' && input.startsWith("sqrt", i)) {
                i += 4; // Move past "sqrt"
                int start = i;
                int openParentheses = 1;
                while (openParentheses > 0) {
                    i++;
                    if (input.charAt(i) == '(') openParentheses++;
                    if (input.charAt(i) == ')') openParentheses--;
                }
                double sqrtValue = evaluate(input.substring(start + 1, i));
                if (sqrtValue < 0) {
                    throw new ArithmeticException("Cannot take square root of a negative number");
                }
                numbers.push(Math.sqrt(sqrtValue));
            } else if (c == '-' && (i == 0 || input.charAt(i - 1) == '(')) {
                StringBuilder sb = new StringBuilder("-");
                i++;
                while (i < length && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    sb.append(input.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int precedence(char operator) {
        switch (operator) {
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

    private static double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    return Double.NaN;
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                return 0;
        }
    }

    public static void storeInMemory(double value) {
        if (calculatorMemory.size() >= MAX_MEMORY) {
            calculatorMemory.remove(calculatorMemory.size() - 1); // Remove the oldest entry
        }
        calculatorMemory.add(0, value); // Add the new value to the start
    }

    public static void clearMemory() {
        calculatorMemory.clear();
    }

    public static Double recallMemory() {
        if (calculatorMemory.isEmpty()) {
            return Double.NaN;
        }
        return calculatorMemory.get(0); // Get the most recent entry
    }

    public static String recallAllMemory() {
        if (calculatorMemory.isEmpty()) {
            return "No values stored in memory.";
        }
        StringBuilder output = new StringBuilder("Stored values: ");
        for (int i = 0; i < calculatorMemory.size(); i++) {
            output.append(calculatorMemory.get(i));
            if (i < calculatorMemory.size() - 1) {
                output.append(", ");
            }
        }
        return output.toString();
    }
}
