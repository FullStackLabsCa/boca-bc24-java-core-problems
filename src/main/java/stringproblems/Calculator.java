package stringproblems;
import java.util.Scanner;
import java.util.Stack;
public class Calculator {
    public static void clearMemory() {
    }
    public static double calculate(String expression) {// main method to calculate the result of a given expression
        try {
            return evaluate(expression);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());// arithmetic error
            throw e; // Rethrow the exception
        } catch (IllegalArgumentException e) {
            throw e; //invalid argument exception
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return Double.NaN; // Return NaN value for divisible zero
        }
    }

    // Evaluate the expression and return the result
    private static double evaluate(String expression) throws Exception {
        expression = expression.replaceAll("\\s+", ""); // Remove whitespace
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i++));
                }
                numbers.push(Double.parseDouble(num.toString()));
                continue;  // skipping  the rest of the loop
            }

            if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    calculate(numbers, operators);
                }
                if (operators.isEmpty() || operators.pop() != '(') {
                    throw new IllegalArgumentException("Mismatched parentheses.");
                }
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    calculate(numbers, operators);
                }
                operators.push(c);
            } else if (expression.startsWith("sqrt(", i)) {

                int endIndex = expression.indexOf(')', i);
                if (endIndex == -1) throw new IllegalArgumentException("Mismatched parentheses.");
                double value = evaluate(expression.substring(i + 5, endIndex));
                numbers.push(Math.sqrt(value));
                i = endIndex;
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
            i++;
        }

        while (!operators.isEmpty()) {
            calculate(numbers, operators);
        }

        if (numbers.size() != 1) {
            return Double.NaN;
        }
        return numbers.pop();
    }

    private static void calculate(Stack<Double> numbers, Stack<Character> operators) throws Exception {  // get an operator to the top two numbers in the stack
        if (numbers.size() < 2) {
            throw new ArithmeticException("Invalid expression.");
        }
        double b = numbers.pop();
        double a = numbers.pop();
        char op = operators.pop();

        double result = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) {
                    numbers.push(Double.NaN);
                }
                yield a / b;
            }
            case '^' -> Math.pow(a, b);
            default -> throw new Exception("Unsupported operator: " + op);
        };

        numbers.push(result);
    }


    private static int precedence(char op) {// Get the precedence of an operator
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Enter a value string to calculate");
        calculate(input);
    }
}
