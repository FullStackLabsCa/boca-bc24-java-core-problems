package problems.calc;

import java.util.*;

public class Calculator {
    private static Double memory = null;
    private static final List<Double> memoryList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression: ");
        String input = scanner.nextLine().trim();

        try {
            double result = calculate(input);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static double calculate(String expr){
        expr = expr.replaceAll(" ", "");

        if (expr.endsWith("M+")) {
            expr = expr.substring(0, expr.length() - 2);
            double result = calculate(expr);
            storeInMemory(result);
            return result;
        }

        if (expr.contains("(")) {
            int openBracket = expr.lastIndexOf("(");
            int closeBracket = expr.indexOf(")", openBracket);
            String innerExpression = expr.substring(openBracket + 1, closeBracket);

            double result = calculate(innerExpression);
            expr = expr.substring(0, openBracket) + result + expr.substring(closeBracket + 1);

            return calculate(expr);
        } else {
            return evaluateExpression(expr);
        }
    }

    public static double evaluateExpression(String expr) {
        if (expr.contains("sqrt")) {
            int openBracket = expr.indexOf("(");
            int closeBracket = expr.indexOf(")", openBracket);
            String numberStr = expr.substring(openBracket + 1, closeBracket);
            double number = Double.parseDouble(numberStr);
            return Math.sqrt(number);
        }

        if (expr.contains("^")) {
            String[] parts = expr.split("\\^");
            double base = Double.parseDouble(parts[0]);
            double exponent = Double.parseDouble(parts[1]);
            return Math.pow(base, exponent);
        }
        return evaluateBasicOperations(expr);
    }

    public static double evaluateBasicOperations(String expr){
        List<Character> operation = new ArrayList<>();
        List<Double> numbers = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operation.add(ch);
                numbers.add(Double.parseDouble(number.toString()));
                number.setLength(0);
            } else {
                number.append(ch);
            }
        }

        if (!number.isEmpty()) {
            numbers.add(Double.parseDouble(number.toString()));
        }

        double result = numbers.get(0);
        for (int j = 0; j < operation.size(); j++) {
            char op = operation.get(j);
            double nextNumber = numbers.get(j + 1);

            switch (op) {
                case '+':
                    result += nextNumber;
                    break;
                case '-':
                    result -= nextNumber;
                    break;
                case '*':
                    result *= nextNumber;
                    break;
                case '/':
                    result /= nextNumber;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public static void storeInMemory(double value) {
        memory = value;
        if (memoryList.size() == 5) {
            memoryList.remove(0);
        }
        memoryList.add(value);
    }

    public static Double recallMemory(){
        return memory;
    }

    public static void clearMemory() {
        memory = null;
        memoryList.clear();
    }

    public static String recallAllMemory() {
        return "Stored values: " + memoryList;
    }
}
