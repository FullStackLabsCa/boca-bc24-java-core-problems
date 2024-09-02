package problems.collectionproblems;

import java.util.*;

public class Calculator {
    private static final List<Double> memory = new ArrayList<>();

    public static double calculate(String expression) {
        if (expression == null || expression.isEmpty()) {
            return Double.NaN;
        }

        if (expression.endsWith("M+")) {
            expression = expression.replace("M+", "").trim();
            double result = evaluateExpression(expression);
            storeInMemory(result);
            return result;
        }

        expression = handleSquareRoots(expression);

        if (expression.matches(".*[^0-9+\\-*/().^\\s].*")) {
            return Double.NaN;
        }

        double result = evaluateExpression(expression);

        if (Double.isInfinite(result)) {
            return Double.NaN;
        }

        return result;
    }


    private static String handleSquareRoots(String expression) {
        while (expression.contains("sqrt(")) {
            int startIndex = expression.indexOf("sqrt(") + 5;
            int endIndex = findClosingBracket(expression, startIndex);
            String sqrtExpression = expression.substring(startIndex, endIndex);
            double sqrtResult = Math.sqrt(evaluateExpression(sqrtExpression));
            expression = expression.replace("sqrt(" + sqrtExpression + ")", String.valueOf(sqrtResult));
        }
        return expression;
    }

    private static int findClosingBracket(String expression, int startIndex) {
        int openBrackets = 1;
        int i = startIndex;
        while (i < expression.length() && openBrackets > 0) {
            if (expression.charAt(i) == '(') {
                openBrackets++;
            } else if (expression.charAt(i) == ')') {
                openBrackets--;
            }
            i++;
        }
        return i - 1;
    }

//    private static double evaluateExpression(String expression) {
//        Deque<Double> numbers = new ArrayDeque<>();
//        Deque<Character> operations = new ArrayDeque<>();
//
//        int len = expression.length();
//        for (int i = 0; i < len; i++) {
//            char c = expression.charAt(i);
//
//            if (Character.isDigit(c) || c == '.') {
//                StringBuilder sb = new StringBuilder();
//                while (i < len && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
//                    sb.append(expression.charAt(i++));
//                }
//                i--;
//                numbers.push(Double.parseDouble(sb.toString()));
//            } else if (c == '(') {
//                operations.push(c);
//            } else if (c == ')') {
//                while (operations.peek() != '(') {
//                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
//                }
//                operations.pop();
//            } else if (isOperator(c)) {
//                while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
//                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
//                }
//                operations.push(c);
//            }
//        }
//
//        while (!operations.isEmpty()) {
//            numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
//        }
//
//        return numbers.pop();
//    }
    private static double evaluateExpression(String expression) {
        Deque<Double> numbers = new ArrayDeque<>();
        Deque<Character> operations = new ArrayDeque<>();

        int len = expression.length();

        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();

                while (i < len && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));

            } else if (c == '(') {
                operations.push(c);
            } else if (c == ')') {
                while (operations.peek() != '(') {
                    if (numbers.size() < 2) {
                        throw new ArithmeticException("Invalid expression.");
                    }
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();

            } else if (isOperator(c)) {
                while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
                    if (numbers.size() < 2) {
                        throw new ArithmeticException("Invalid expression.");
                    }
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(c);
            }
        }

        while (!operations.isEmpty()) {
            if (numbers.size() < 2) {
                throw new ArithmeticException("Invalid expression.");
            }
            numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
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

    private static double applyOperation(char operation, double b, double a) {
        double result = 0;

        boolean applied = false;

        while (!applied) {
            if (operation == '+') {
                result = a + b;
                applied = true;
            } else if (operation == '-') {
                result = a - b;
                applied = true;
            } else if (operation == '*') {
                result = a * b;
                applied = true;
            } else if (operation == '/') {
                if (b == 0) {
                    return Double.NaN;
                }
                result = a / b;
                applied = true;

            } else if (operation == '^') {
                result = Math.pow(a, b);
                applied = true;
            }
        }

        return result;
    }

    public static void storeInMemory(double value) {
        if (memory.size() >= 5) {
            memory.remove(0);
        }
        memory.add(value);
    }

    public static double recallMemory() {
        if (memory.isEmpty()) {
            System.out.println("No values stored in memory.");
            return Double.NaN;
        }

        return memory.get(memory.size() - 1);
    }

    public static void clearMemory() {
        memory.clear();
    }

    public static String recallAllMemory() {
        if (memory.isEmpty()) {
            return "No values stored in memory.";
        }

        StringBuilder sb = new StringBuilder("Stored values: ");
        for (int i = memory.size() - 1; i >= 0; i--) {
            sb.append(memory.get(i));
            if (i > 0) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println(calc.calculate("3 + 5"));
        System.out.println(calc.calculate("10 - 2 * 3"));
        System.out.println(calc.calculate("(10 + 2) * 3"));
        System.out.println(calc.calculate("sqrt(25) + 3 ^ 2"));
        System.out.println(calc.calculate("10 / 0"));
        calc.calculate("3 + 5 M+");
        System.out.println(calc.recallMemory());
        calc.clearMemory();
    }
}