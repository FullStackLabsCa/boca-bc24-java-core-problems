package stringproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    static int MEMORY_SIZE = 5;
    static double[] memory = new double[MEMORY_SIZE];
    static int memoryIndex = 0;

    public static double calculate (String expression) {
       expression= expression.replaceAll("\\s", "");
        if (expression.endsWith("M+")) {
            return handleMemoryOperation(expression);
        }
            expression = removeSqrt(expression);
            expression = removePower(expression);
            return parseExpression(expression);
        }

    public static double parseExpression (String input) {
        String expression = input.replaceAll("\\s","");
        List<Double> values = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        int i = 0;
        while ( i < expression.length()) {

            if(expression.charAt(i) == '(') {
                operators.add(expression.charAt(i));
                i++;

            } else if (isDigit(expression.charAt(i)) || expression.charAt(i) == '.') {

                StringBuilder sb = new StringBuilder();

                while (i < expression.length() && (isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                values.add(Double.parseDouble(sb.toString()));

            } else if(expression.charAt(i) == ')') {
                while (operators.get(operators.size() - 1) != '(') {
                    values.add(doCalculation(operators.remove(operators.size() - 1), values.remove(values.size() - 2), values.remove(values.size() - 1)));
                }
                operators.remove(operators.size() - 1);
                i++;

            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/'){
                while (!operators.isEmpty() && hasPriority(expression.charAt(i), operators.get(operators.size() - 1))) {
                    values.add(doCalculation(operators.remove(operators.size() - 1), values.remove(values.size() - 2), values.remove(values.size() - 1)));
                }
                operators.add(expression.charAt(i));
                i++;
            }

        }

        while (!operators.isEmpty()) {
            values.add(doCalculation(operators.remove(operators.size() - 1), values.remove(values.size() - 2), values.remove(values.size() - 1)));
        }
        return values.get(0);
    }

    private static boolean hasPriority (char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static double doCalculation (char op, double a, double b) {

        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    System.out.println("Division by zero is not allowed.");
                    return Double.NaN;
                }
                return a / b;
            default: return -1;
        }
    }

    private static String removeSqrt(String expression) {
        while (expression.contains("sqrt(")) {
            int startIndex = expression.indexOf("sqrt(");
            int endIndex = expression.indexOf(')', startIndex);

            String valueEx = expression.substring(startIndex + 5, endIndex);

            double value = Double.parseDouble(valueEx);

            if (value < 0) {
                throw new ArithmeticException("Negative values not allowed");

            }else if(value > 0 ) {
                expression = expression.substring(0, startIndex) + Math.sqrt(value) + expression.substring(endIndex + 1);
                return expression;
            }
        }

        return expression;
    }

    private static String removePower(String expression) {
        int index;
        while ((index = expression.indexOf('^')) != -1) {
            int leftIndex = index - 1;
            int rightIndex = index + 1;
            while (leftIndex >= 0 && (Character.isDigit(expression.charAt(leftIndex)) || expression.charAt(leftIndex) == '.')) {
                leftIndex--;
            }

            leftIndex++;
            String base = expression.substring(leftIndex, index);
            int endRightIndex = rightIndex;
            while (endRightIndex < expression.length() && (Character.isDigit(expression.charAt(endRightIndex)) || expression.charAt(endRightIndex) == '.')) {
                endRightIndex++;
            }

            String exponent = expression.substring(index + 1, endRightIndex);
            double baseValue = Double.parseDouble(base);
            double exponentValue = Double.parseDouble(exponent);
            double result = Math.pow(baseValue, exponentValue);
            expression = expression.substring(0, leftIndex) + result + expression.substring(endRightIndex);
        }
        return expression;
    }

    private static double handleMemoryOperation(String expression) {
        String expr = expression.substring(0, expression.length() - 2).trim();
        double result = parseExpression(expr);
        storeInMemory(result);
        return result;
    }

    public static void storeInMemory(double value) {
        memory[memoryIndex % MEMORY_SIZE] = value;
        memoryIndex++;
    }

    public static Double recallMemory() {
        if (memoryIndex == 0) {
            System.out.println("No value stored in memory.");
            return null;
        }
        return memory[(memoryIndex - 1) % MEMORY_SIZE];
    }

    public static void clearMemory() {
        Arrays.fill(memory, 0);
        memoryIndex = 0;
    }

    public static String recallAllMemory() {
        if (memoryIndex == 0) {
            return "No values stored in memory.";
        }
        StringBuilder sb = new StringBuilder("Stored values: ");

        int start = memoryIndex < MEMORY_SIZE ? 0 : memoryIndex % MEMORY_SIZE;

        for (int i = start; i < memoryIndex && i < start + MEMORY_SIZE; i++) {
            sb.append(memory[i % MEMORY_SIZE]);
            if (i < memoryIndex - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    public static boolean isDigit (char digit){
        if(digit == '0'|| digit == '1'||digit == '2'||digit == '3'||digit == '4'||digit == '5'||digit == '6'||digit == '7'||digit == '8'||digit == '9'){
            return true;
        }
        return false;
    }

}
