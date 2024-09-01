package calculator;

import java.util.Scanner;

import static java.lang.Double.NaN;

public class Calculator {
    static double[] memory = new double[5];
    static int index = 0, count = 0;

    // Check whether expression is valid or not, but not working need to fix it
    public static boolean isValidString(String expression) {
        boolean isValid = false;

        String regex = "^"
                + "(\\d+(\\.\\d+)?)"
                + "(\\s*[+\\-*/^]\\s*\\d+(\\.\\d+)?)*"
                + "(\\s*\\([\\d+*/^\\.\\s-]+\\))?"
                + "(\\s*sqrt\\(\\d+(\\.\\d+)?\\))?"
                + "(\\s*[a-zA-Z]+\\s*[^\\d\\s])?"
                + "$";
        if (expression.matches("^\\d+(\\.\\d+)?(\\s*[+\\-*/^]\\s*\\d+(\\.\\d+)?)*(\\s*\\([\\d+*/^\\.\\s-]+\\))$")) {
            isValid = true;
        }
        return isValid;
    }

    // Calculate expression
    public static double calculate(String expression) {
        try {
            double result = 0;
            if (expression.contains("M+")) {
                expression = expression.replace("M+", "");
                result = handleConditionalExpression(expression);
                storeInMemory(result);
                System.out.println(recallMemory());
            } else result = handleConditionalExpression(expression);
            return result;
        } catch (ArithmeticException e) {
            if (e.getMessage() == "DIVIDE_BY_ZERO") {
                System.out.println("Division by zero is not allowed.");
                return NaN;
            } else if (e.getMessage() == "NEGATIVE_VALUE") {
                System.out.println("Square root of a negative number is not allowed.");
                throw new ArithmeticException();
            }
            System.out.println("Remaining all arithmetic exception::" + e.getMessage());

        } catch (Exception e) {
            System.out.println("Invalid expression.");
        }
        return 0;
    }

    // Check operator priority
    public static int checkPriority(char operator) {
        int operatorPriority = switch (operator) {
            case '*', '/' -> 1;
            case '+', '-' -> 2;
            case '^' -> 3;
            default -> 0;
        };
        return operatorPriority;
    }

    // Perform arithmetic operation
    public static double performOperation (char operator, double operand1, double operand2) throws Exception{
        double result = 0;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("DIVIDE_BY_ZERO");
                }
                result = operand1 / operand2;
                break;
            case '^':
                if (operand2 < 0) {
                    System.out.println("Operation not supported.");
                    return 0;
                }
                result = Math.pow(operand1, operand2);
                break;
            default:
                System.out.println("Invalid Expression.");
                break;
        }
        return result;
    }

    // Handle expression based on various condition
    public static double handleConditionalExpression(String expression) throws Exception {
        double [] operands = new double[expression.length()];
        char [] operators = new char[expression.length()];
        int operandsIndex = 0, operatorIndex = 0;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (expression.startsWith("sqrt", i)) {
                i += 4;
                int start = i + 1;
                int end = expression.indexOf(')', start);
                if (end == -1) {
                    throw new Exception("Invalid expression.");
                }
                double value = Double.parseDouble(expression.substring(start, end));
                if (value < 0) {
                    throw new ArithmeticException("NEGATIVE_VALUE");
                }
                operands[operandsIndex++] = Math.sqrt(value);
                i = end;
            } else if (ch == '(') {
                operators[operatorIndex++] = ch;
            } else if (ch == ')') {
                if (operatorIndex > 0 && operators[operatorIndex - 1] != '(') {
                    operandsIndex--;
                    char operator = operators[--operatorIndex];
                    operands[operandsIndex - 1] = performOperation(operator, operands[operandsIndex - 1], operands[operandsIndex]);
                }
                operatorIndex--;
            } else if((Character.isDigit(ch) || ch == '.')) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                operands[operandsIndex] = Double.parseDouble(sb.toString());
                operandsIndex++;
                i--;
            } else if(String.valueOf(ch).matches("[+*/^-]")) {
                if (operatorIndex > 0 && operators[operatorIndex - 1] != '(') {
                    if (ch != '^' && checkPriority(operators[operatorIndex - 1]) <= checkPriority(ch)) {
                        operandsIndex--;
                        char operator = operators[--operatorIndex];
                        operands[operandsIndex - 1] = performOperation(operator, operands[operandsIndex - 1], operands[operandsIndex]);
                    } else if (operators[operatorIndex - 1] == '^') {
                        operandsIndex--;
                        char operator = operators[--operatorIndex];
                        operands[operandsIndex - 1] = performOperation(operator, operands[operandsIndex - 1], operands[operandsIndex]);
                    }
                }
                operators[operatorIndex++] = ch;
            }
        }

        while (operatorIndex > 0) {
            operandsIndex--;
            char operator = operators[--operatorIndex];
            operands[operandsIndex - 1] = performOperation(operator, operands[operandsIndex - 1], operands[operandsIndex]);
        }

        return operands[0];
    }

    // Clear memory
    public static void clearMemory() {
        memory = new double[5];
        index = 0;
        count = 0;
    }

    // Recall memory
    public static double recallMemory() {
        if (count == 0) {
            System.out.println("No value stored in memory.");
            return 0;
        } else {
            return memory[count - 1];
        }
    }

    // Store value in memory
    public static void storeInMemory(double value) {
        if (index >= 5) {
            index = 0;
            count--;
        }
        memory[index++] = value;
        count++;
    }

    // Recall all memory
    public static String recallAllMemory() {
        if (count == 0) {
            return "No values stored in memory.";
        }
        StringBuilder sb = new StringBuilder("Stored values: ");
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(", ");
            sb.append(memory[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine().trim();

//        boolean isValidString = isValidString(expression.trim());
//        System.out.println("isValidString==" + isValidString);
//        if (!isValidString) {
//            System.out.println("ERROR: INVALID STRING");
//            return;
//        }

        System.out.println(calculate(expression));
    }
}
