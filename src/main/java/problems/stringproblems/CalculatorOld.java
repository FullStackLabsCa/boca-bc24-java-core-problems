package problems.stringproblems;


import java.util.Scanner;

public class CalculatorOld {

    public String calculate(String expression) {
        //identify the operator:
        String operator = " ";
        char charInExpression = 0;
        for (int i = 0; i < expression.length(); i++) {
            charInExpression = expression.charAt(i);
            if (charInExpression == '+' || charInExpression == '-' || charInExpression == '*' || charInExpression == '/') {
                operator = String.valueOf(charInExpression);
                break;
            }
        }

        String operatorToEscape = "\\" + operator;

        //trimming to remove any extra spaces
        expression = expression.trim();

        //splitting string in to identify two operands
        String[] splittingExpression = expression.split("\\s*" + operatorToEscape + "\\s*"); // \\s* for handling spaces around the operator
        String operandOne = splittingExpression[0].trim();
        String operandTwo = splittingExpression[1].trim();

        //converting Operands to numbers
        double operandOneNumber = Double.parseDouble(operandOne);
        double operandTwoNumber = Double.parseDouble(operandTwo);


        //Arithmetic operations
        if (operator.equals("+")) {
            double addition = operandOneNumber + operandTwoNumber;
            System.out.println("Result: " + addition);
        } else if (operator.equals("-")) {
            double subtraction = operandOneNumber - operandTwoNumber;
            System.out.println("Result: " + subtraction);
        } else if (operator.equals("*")) {
            double multiplication = operandOneNumber * operandTwoNumber;
            System.out.println("Result: " + multiplication);
        } else if (operator.equals("/")) {
            if (operandTwoNumber == 0) {
                System.out.println("Division by zero is not allowed.");
            } else {
                double division = operandOneNumber / operandTwoNumber;
                System.out.println("Result: " + division);

            }

        }
    return expression;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an operation like 2.1 + 3.5");
        String userChoiceOfOperation = scanner.nextLine();
        CalculatorOld calculator = new CalculatorOld();
        calculator.calculate(userChoiceOfOperation);

    }
}
