package problems.calculator;

import java.util.*;

public class Calculator {

    // Public method to perform calculations
    public static double calculate(String expression) {
        expression = expression.trim();
        List<String> tokens = tokenSize(expression);
        List<String> postfix = infixToPostfix(tokens);
        return evaluatePostfix(postfix);

//  Infix expression:  a operator b (a + b)
//  Postfix expression: a b operator (ab+) Examples: Input: A + B * C + D.
    }

    // Private methods for internal calculations and conversions
    private static List<String> tokenSize(String expression) {
        String[] parts = expression.split(" ");
        return new ArrayList<>(Arrays.asList(parts));
    }

    private static List<String> infixToPostfix(List<String> infix) {
        Stack<String> operators = new Stack<>();
        List<String> postfix = new ArrayList<>();

        for (String token : infix) {
            if (isNumber(token)) {
                postfix.add(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(token) <= precedence(operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    postfix.add(operators.pop());
                }
                operators.pop();
            } else if (token.startsWith("sqrt(")) {
                postfix.add(handleSqrt(token));
            } else if (token.equals("M+")) {
                storeInMemory(Double.parseDouble(postfix.get(postfix.size() - 1)));
            }
        }

        while (!operators.isEmpty()) {
            postfix.add(operators.pop());
        }

        return postfix;
    }

    private static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                double result = performOperation(a, b, token);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static double performOperation(double a, double b, String operator) {
        switch (operator) {
            case "^":
                return ArithmeticOperations.power(a, b);
            case "/":
                return ArithmeticOperations.division(a, b);
            case "*":
                return ArithmeticOperations.multiplication(a, b);
            case "+":
                return ArithmeticOperations.addition(a, b);
            case "-":
                return ArithmeticOperations.subtraction(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static String handleSqrt(String token) {
        String number = token.replace("sqrt(", "").replace(")", "");
        double result = ArithmeticOperations.sqrt(Double.parseDouble(number));
        return String.valueOf(result);
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    // Memory Management - Using Stack
    private static final int MEMORY_LIMIT = 5;
    private static Stack<Double> memory = new Stack<>();

    // Public method to store a value in memory
    public static void storeInMemory(double value) {
        if (memory.size() >= MEMORY_LIMIT) {
            memory.remove(0); // Remove the oldest value to maintain the limit
        }
        memory.push(value); // Add the new value to the stack
        System.out.println("Added " + value);
    }

    // Public method to recall the most recent value from memory
    public static Double recallMemory() {
        if (memory.isEmpty()) {
            System.out.println("No values stored in memory.");
            return null;
        }
        return memory.peek(); // Get the top value of the stack without removing it
    }

    // Public method to clear memory
    public static void clearMemory() {
        memory.clear(); // Clears all values from the stack
        System.out.println("Memory cleared.");
    }

    // Public method to recall all values from memory
    public static String recallAllMemory() {
        if (memory.isEmpty()) {
            return "No values stored in memory.";
        }
        // Create a copy of the stack and reverse it to maintain the order
        Stack<Double> reversedMemory = (Stack<Double>) memory.clone();
        Collections.reverse(reversedMemory);
        return "Stored values: " + String.join(", ", reversedMemory.toString().replace("[", "").replace("]", ""));
    }



    public static void main(String[] args) {
        // Perform basic operations
        System.out.println("Basic Operations:");
        System.out.println("3.5 + 2.1 = " + calculate("3.5 + 2.1"));
        System.out.println("10 - 4 = " + calculate("10 - 4"));
        System.out.println("6 * 7 = " + calculate("6 * 7"));
        System.out.println("8 / 2 = " + calculate("8 / 2"));

        // Test division by zero
        System.out.println("Division by zero:");
        try {
            System.out.println("10 / 0 = " + calculate("10 / 0"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero not allowed");
        }

        // Test multiple operations
        System.out.println("Multiple Operations:");
        System.out.println("3 + 5 * 2 - 4 / 2 = " + calculate("3 + 5 * 2 - 4 / 2"));

        // Test parentheses
        System.out.println("Parentheses:");
        try {
            System.out.println("3 + (2 * 4) - 5 = " + calculate("3 + (2 * 4) - 5"));
            System.out.println("(10 + 2) * 3 = " + calculate("(10 + 2) * 3"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test advanced operations
        System.out.println("Advanced Operations:");
        System.out.println("sqrt(16) = " + calculate("sqrt(16)"));
        System.out.println("2 ^ 3 = " + calculate("2 ^ 3"));

        // Test memory operations
        System.out.println("Memory Operations:");
        storeInMemory(8.0);
        System.out.println("Stored value: " + recallMemory());
        storeInMemory(15.5);
        System.out.println("Stored value: " + recallMemory());
        System.out.println("Recall all memory: " + recallAllMemory());
        clearMemory();
        System.out.println("Memory after clearing: " + recallAllMemory());
    }


}


/*

// Memory Management - Using List
private static final int MEMORY_LIMIT = 5;
private static List<Double> memory = new ArrayList<>();

// Public method to store a value in memory
public static void storeInMemory(double value) {
    if (memory.size() >= MEMORY_LIMIT) {
        memory.remove(0);
    }
    memory.add(value);
    System.out.println("Added " + value);
}

// Public method to recall the most recent value from memory
public static Double recallMemory() {
    if (memory.isEmpty()) {
        System.out.println("No values stored in memory.");
        return null;
    }
    return memory.get(memory.size() - 1);
}

// Public method to clear memory
public static void clearMemory() {
    memory.clear();
    System.out.println("Memory cleared.");
}

// Public method to recall all values from memory
public static String recallAllMemory() {
    if (memory.isEmpty()) {
        return "No values stored in memory.";
    }
    return "Stored values: " + String.join(", ", memory.toString().replace("[", "").replace("]", ""));
}


*/
