package problems.arraysandstrings;

import java.util.*;

public class UpgradedCalculator {

    double[] memory = new double[5];
    int index = 0;

    public void storeInMemory(double value) {
//        if(index  ){
//            memory[index++] = value;
//        }
    }

    public static void main(String[] args) throws Exception {


        try {
            Scanner scanner = new Scanner(System.in);
            String expression = scanner.nextLine().replaceAll("\\s", "");
            double result = 0;
            if (expression.contains("M+")) {
                expression = expression.replace("M+", "");

                result = solveExpression(expression);
            } else result = solveExpression(expression);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static double solveExpression(String expression) throws Exception {
        Character[] operators = new Character[expression.length()];
        Double[] values = new Double[expression.length()];
        int opIndex = 0;
        int valueIndex = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (expression.startsWith("sqrt", i)) {
                i = i + 4;
                int start = i + 1;
                int end = expression.indexOf(')', start);
                if (end == -1) throw new Exception("Invalid expression.");
                double value = Double.parseDouble(expression.substring(start, end));
                if (value < 0) throw new Exception("Square root of a negative number is not allowed.");
                values[valueIndex++] = Math.sqrt(value);
                i = end;
            } else if (c == '(') {
                operators[opIndex++] = c;
            } else if (c == ')') {
                while (opIndex > 0 && operators[opIndex - 1] != '(') {
                    valueIndex--;
                    values[valueIndex - 1] = performOperation(operators[--opIndex], values[valueIndex], values[valueIndex - 1]);
                }
                opIndex--;
            } else if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                values[valueIndex++] = Double.parseDouble(sb.toString());
                i--;
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (opIndex > 0 && operatorPriority(c) <= operatorPriority(operators[opIndex - 1])) {
                    valueIndex--;
                    values[valueIndex - 1] = performOperation(operators[--opIndex], values[valueIndex], values[valueIndex - 1]);
                }
                operators[opIndex++] = c;
            }
        }

        while (opIndex > 0) {
            valueIndex--;
            values[valueIndex - 1] = performOperation(operators[--opIndex], values[valueIndex], values[valueIndex - 1]);
        }

        if (valueIndex == 0) throw new Exception("Invalid Expression");

        return values[valueIndex - 1];
    }

    public static int operatorPriority(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    public static double performOperation(char operator, double valueTwo, double valueOne) throws Exception {
        return switch (operator) {
            case '+' -> valueOne + valueTwo;
            case '-' -> valueOne - valueTwo;
            case '*' -> valueOne * valueTwo;
            case '/' -> {
                if (valueTwo == 0) throw new ArithmeticException("Division by zero is not allowed.");
                yield valueOne / valueTwo;
            }
            case '^' -> Math.pow(valueOne, valueTwo);
            default -> throw new Exception("Invalid Expression.");
        };
    }

}
