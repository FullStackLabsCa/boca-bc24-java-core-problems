package arrays;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static void main(String[] args) {
        String expression = "3 + (2 * 4) - 5".replaceAll("\\s", "").toLowerCase();
        //calculateSquareRoot(expression);
        Stack<Double> numb = new Stack<>();
        Stack<Character> operation = new Stack<>();
        String ValidNumber = "[0-9]*";
        for (int current = 0; current < expression.length()-1; current++) {
            if (expression.charAt(current) == '+' ||
                    expression.charAt(current) == '-' ||
                    expression.charAt(current) == '+' ||
                    expression.charAt(current) == '/' ||
                    expression.charAt(current) == '*' ||
                    expression.charAt(current) == '(' ||
                    expression.charAt(current) == ')'
            ){
                operation.push(expression.charAt(current));
            } else {

                numb.push(Double.parseDouble(String.valueOf(expression.charAt(current))));
            }
        }



        char value;
        double result = 0;
        double value1;
        double value2;
//        for (int expValue = 0; expValue < expression.length(); expValue++) {
//            result = getExpressionResult(expression, expValue, result, calculation);
//            System.out.println(result);
//        }
//        System.out.println(result);
    }

    private static void calculateSquareRoot(String expression) {
        String sqrtRegex = "sqrt[(][0-9]*[)]";
        Pattern pattern = Pattern.compile(sqrtRegex);
        Matcher matcher = pattern.matcher(expression);
        String fullMatch = "";
        if (matcher.find()) {
            fullMatch = matcher.group();
            System.out.println("Full match: " + fullMatch);
        } else {
            System.out.println("Invalid Sqrt Expression found.");
        }

        double squrtResult = Math.sqrt(Double.parseDouble(fullMatch.replaceAll("\\D", "")));
        System.out.println(squrtResult);
    }

    private static double getExpressionResult(String expression, int expValue, double result, Stack<Double> calculation) {
        switch (expression.charAt(expValue)) {
            case '+':
                result += calculation.pop();
                break;
            case '-':
                result = result - calculation.pop();
                break;
            case '*':
                result = result * calculation.pop();
                break;
            case '/':
                result = result / calculation.pop();
                break;
            case '(':
                break;
            case ')':
                break;
            default:
                calculation.push(Double.parseDouble(String.valueOf(expression.charAt(expValue))));
        }
        return result;
    }
}
