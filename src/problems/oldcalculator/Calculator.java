package problems.oldcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
    Important notes:
        - Parsing the input string.
        - Handling arithmetic operations in order of precedence.
        - Managing memory operations (M+, recall, clear).
 */
public class Calculator {

    private static List<Double> storedMemoryList = new ArrayList<>();

    // Store value in memory
    public static void storeInMemory(double value) {
        if (storedMemoryList.size() < 5) {
            storedMemoryList.add(value);
            System.out.println("Added " + value);
        } else {
            System.out.println("Removing: " + storedMemoryList.get(0));
            storedMemoryList.remove(0);
            storedMemoryList.add(value);
            System.out.println("Added " + value);
        }
    }

    // Recall the last value from memory
    public static Double recallMemory() {
        if (storedMemoryList.isEmpty()) {
            System.out.println("No values stored in memory.");
            return null;
        }
        return storedMemoryList.get(storedMemoryList.size() - 1);
    }

    // Clear all values from memory
    public static void clearMemory() {
        storedMemoryList.clear();
        System.out.println("Memory cleared.");
    }

    // Display all stored values in memory
    public static String recallAllMemory() {
        if (storedMemoryList.isEmpty()) {
            return "No values stored in memory.";
        }
        return "Stored values: " + storedMemoryList.toString().replace("[", "").replace("]", "");
    }

    public static double calculate(String args) {

//        String expression = "5 + ( 6 * 2 - 4 )"; // exp-1 - for checking List<String> parsedToken + parseInput method
//        List<String> parsedTokens = parseInput(expression); // taking exp-1
//        System.out.println(parsedTokens);


//        String expression = "5 + 6 - 4"; // exp-2 - for checking evaluateExpression method
//        List<String> parsedTokens = parseInput(expression); // taking exp-1
//        double result = evaluateExpression(parsedTokens); // taking exp-2
//        System.out.println("Result: " + result);


//        String expression = "5 + ( 6 * 2 - 4 ) / 2"; // exp-3 - for *,/ and ( )
//        double result = evaluateParentheses(expression); // taking exp-3
//        System.out.println("Result: " + result);

//        String expression = "sqrt(9) + 6 * 2 - 4";
//        double result = evaluateParentheses(expression);
//        System.out.println("Result: " + result);

//        String expression = "sqrt(9) + 6 * 2 - 4";
//        List<String> tokens = parseInput(expression);
//        System.out.println("Tokens before sqrt evaluation: " + tokens); // Debugging line
//        tokens = evaluateSquareRoot(tokens);
//        System.out.println("Tokens after sqrt evaluation: " + tokens); // Debugging line
//        double result = evaluateExpression(tokens);
//        System.out.println("Result: " + result);

        String expression = "sqrt(9) + 6 * 2 - 4";
        List<String> tokens = parseInput(expression);
//        System.out.println("Tokens before sqrt evaluation: " + tokens); // Debugging line
        tokens = evaluateSquareRoot(tokens);
//        System.out.println("Tokens after sqrt evaluation: " + tokens); // Debugging line
        tokens = evaluatePrecedence(tokens);
//        System.out.println("Tokens after precedence evaluation: " + tokens); // Debugging line
        double result = evaluateExpression(tokens);
        System.out.println("Result: " + result); //

        storeInMemory(10.0);
        storeInMemory(20.0);
//        System.out.println("Recall one Value: " + recallMemory()); // Should print 20.0
//        System.out.println(recallAllMemory()); // Should print stored values
        clearMemory();

        return result;
    }


    // Method to parse the input string into a list of tokens
    private static List<String> parseInput(String expression) {

        // Trim and split the input string by spaces
        String[] tokens = expression.trim().split(" ");
        return new ArrayList<>(Arrays.asList(tokens));
    }

    // Method to evaluate expression with + and - operators
    private static double evaluateExpression(List<String> parsedTokens) {
        // First pass: Handle multiplication and division
        List<String> tempTokens = new ArrayList<>();
        int i = 0;
        while (i < parsedTokens.size()) {
            String token = parsedTokens.get(i);
            if (token.equals("*") || token.equals("/")) {
                // Perform multiplication or division
                double operand1 = Double.parseDouble(tempTokens.remove(tempTokens.size() - 1));
                double operand2 = Double.parseDouble(parsedTokens.get(i + 1));
                double result = 0;

                if (token.equals("*")) {
                    result = ArithmeticOperations.multiplication(operand1, operand2);
                } else if (token.equals("/")) {
                    result = ArithmeticOperations.division(operand1, operand2);
                }

                tempTokens.add(String.valueOf(result));
                i += 2; // Skip next operand as it's already used
            } else {
                tempTokens.add(token);
                i++;
            }
        }

        // Handle addition and subtraction
        double finalResult = 0;
        String operator = "+";
        for (String token : tempTokens) {
            switch (token) {
                case "+":
                case "-":
                    operator = token;
                    break;

                default:
                    try {
                        double value = Double.parseDouble(token);
                        if (operator.equals("+")) {
                            finalResult += value;
                        } else if (operator.equals("-")) {
                            finalResult -= value;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing number: " + token);
                    }
                    break;
            }
        }

        return finalResult;
    }

    private static List<String> evaluateSquareRoot(List<String> parsedTokens) {
        List<String> newTokens = new ArrayList<>();
        for (String token : parsedTokens) {
            if (token.startsWith("sqrt(") && token.endsWith(")")) {
                // Extract the content between sqrt( and )
                String inside = token.substring(5, token.length() - 1); // Remove "sqrt(" and ")"
                System.out.println("Extracted inside sqrt: " + inside); // Debugging line
                try {
                    double value = Double.parseDouble(inside);
                    double result = ArithmeticOperations.sqrt(value);
                    newTokens.add(String.valueOf(result));
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing number for sqrt operation: " + inside);
                    newTokens.add(token); // Keep the original token if there's an error
                }
            } else {
                newTokens.add(token);
            }
        }
        return newTokens;
    }

    private static List<String> evaluatePrecedence(List<String> parsedTokens) {
        List<String> newTokens = new ArrayList<>();
        Iterator<String> iterator = parsedTokens.iterator();

        while (iterator.hasNext()) {
            String token = iterator.next();
            if (token.equals("*") || token.equals("/")) {
                double leftOperand = Double.parseDouble(newTokens.remove(newTokens.size() - 1));
                double rightOperand = Double.parseDouble(iterator.next());
                double result = (token.equals("*")) ? ArithmeticOperations.multiplication(leftOperand, rightOperand)
                        : ArithmeticOperations.division(leftOperand, rightOperand);
                newTokens.add(String.valueOf(result));
            } else {
                newTokens.add(token);
            }
        }
        return newTokens;
    }

    // Method to handle parentheses
    private static double evaluateParentheses(String expression) {

        while (expression.contains("(")) {

            int openIndex = expression.indexOf("(");
            int closeIndex = expression.indexOf(")", openIndex);

            String innerExpresssion = expression.substring(openIndex + 1, closeIndex);
            double innerResult = evaluateExpression(parseInput(innerExpresssion));
            expression = expression.substring(0, openIndex) + innerResult + expression.substring(closeIndex + 1);
        }

        return evaluateExpression(parseInput(expression));
    }


}

