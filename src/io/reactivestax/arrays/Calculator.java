package io.reactivestax.arrays;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    static List<Double> memory = new ArrayList<>();
    static double currentResult;
    static int memorySize = 0;
    static final int MAX_MEMORY = 5;

    public static void main(String[] args) {
        String calculator = "\uD83D\uDCF1";
        System.out.println("===========CALCULATOR========== " + calculator);
        System.out.println(calculate("sqrt(-9)"));

    }

    public static double calculate(String expression) {
        boolean memoryOperation = false;
        expression = expression.replaceAll("\\s", "").toLowerCase();
        if (expression.contains("sqrt")) {
            try {
                currentResult = calculateSquareRoot(expression);
                memory.add(currentResult);
                return currentResult;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }


        }
        if (expression.contains("m+")) {
            String pattern = "m\\+";
            expression = expression.replaceAll(pattern, "").trim();
            memoryOperation = true;
        }

        String resultSoFar = "0";
        int openingBracketIndex = 0;
        int closingBracketIndex = 0;
        for (int currentIndex = 1; currentIndex < expression.length(); currentIndex++) {
            if (expression.charAt(currentIndex) == '(') {
                openingBracketIndex = currentIndex;
            } else if (expression.charAt(currentIndex) == ')') {
                closingBracketIndex = currentIndex;
                resultSoFar = expression.substring(openingBracketIndex + 1, closingBracketIndex);
                resultSoFar = solveExpression(resultSoFar);
                expression = expression.substring(0, openingBracketIndex) + resultSoFar + expression.substring(closingBracketIndex + 1);

            }
            if (currentIndex == expression.length() - 1) {
                resultSoFar = solveExpression(expression);
            }

        }
        memory.add(Double.valueOf(resultSoFar));
        return Double.valueOf(resultSoFar);
    }

    private static String solveExpression(String expression) {
        String numberRegex = "\\d+\\.\\d+|\\d+";
        String operatorRegex = "[+*/^-]";

        List<Double> operands = new ArrayList<>();
        List<String> operators = new ArrayList<>();

        Matcher numberMatcher = Pattern.compile(numberRegex).matcher(expression);
        while (numberMatcher.find()) {
            operands.add(Double.parseDouble(numberMatcher.group()));
        }
        Matcher operatorMatcher = Pattern.compile(operatorRegex).matcher(expression);
        while (operatorMatcher.find()) {
            operators.add(operatorMatcher.group());
        }
        String result = subExpressionCalculate(operators, operands);
        return result;

    }

    private static String subExpressionCalculate(List<String> operators, List<Double> operands) {
        int index = 0;
        while (index < operators.size()) {
            String operator = operators.get(index);
            if (operator.equals("^")) {
                double base = operands.get(index);
                double exponent = operands.get(index + 1);
                double result = Math.pow(base, exponent);
                operands.set(index, result);
                operands.remove(index + 1);
                operators.remove(index);
            } else {
                index++;
            }
        }

        index = 0;
        while (index < operators.size()) {
            String operator = operators.get(index);
            if (operator.equals("*") || operator.equals("/")) {
                double firstOperand = operands.get(index);
                double secondOperand = operands.get(index + 1);
                double result = 0;
                if (operator.equals("*")) {
                    result = firstOperand * secondOperand;
                } else if (operator.equals("/")) {
                    if (secondOperand == 0) {
                        return "NaN";
                    }
                    result = firstOperand / secondOperand;
                }
                operands.set(index, result);
                operands.remove(index + 1);
                operators.remove(index);
            } else {
                index++;
            }
        }

        double finalResult = operands.get(0);
        for (int j = 0; j < operators.size(); j++) {
            String operator = operators.get(j);
            double nextOperand = operands.get(j + 1);
            if (operator.equals("+")) {
                finalResult += nextOperand;
            } else if (operator.equals("-")) {
                finalResult -= nextOperand;
            }
        }
        return String.valueOf(finalResult);
    }


    private static Double calculateSquareRoot(String expression){
        String sqrtRegex = "sqrt[(][0-9]*[)]";
        Pattern pattern = Pattern.compile(sqrtRegex);
        Matcher matcher = pattern.matcher(expression);
        String fullMatch = "";
        if (matcher.find()) {
            fullMatch = matcher.group();
        } else {
            throw new ArithmeticException("Invalid sqrt expression found.");
        }

        double squrtResult = Math.sqrt(Double.parseDouble(fullMatch.replaceAll("\\D", "")));
        return squrtResult;
    }

    public static void clearMemory() {
        memory = new ArrayList<>();
    }

    public static Double recallMemory() {
        return memory.get(memory.size() - 1);

    }

    public static void storeInMemory(double v) {
        if (memorySize > MAX_MEMORY) {
            memory.set(0, v);
        } else {
            memory.add(v);
            memorySize++;
        }


    }

    public static String recallAllMemory() {
        String output = "Stored values: ";
        if (memory.size() == 0) {
            return "No values stored in memory.";
        } else {
            for (Double value : memory) {
                if (memory.indexOf(value) == memory.size() - 1) {
                    output += value;
                } else {
                    output += value + ", ";
                }
            }
        }

        return output;
    }
}
