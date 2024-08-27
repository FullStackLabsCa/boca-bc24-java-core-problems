package MathematicsProblem.Calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter expression ( 'x' to quit):");
            String expression = scanner.nextLine();

            if (expression.equalsIgnoreCase("x")) {
                break;
            }
            Calculator calculator = new Calculator();
            String result = calculator.calculate(expression);
            System.out.println(result);
        }
    }

    public String calculate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return "Error: Input is empty or null";
        }
        expression = expression.replaceAll("\\s", "");
        if (expression.length() == 0) {
            return "Error: Input is empty or null";
        }
        try {
            Addition addition = new Addition();
            Subtraction subtraction = new Subtraction();
            Multiplication multiplication = new Multiplication();
            Division division = new Division();
            String[] parts = expression.split("(?<=[-+/*])|(?=[-+/*])");
            System.out.println(parts.length);
            if (parts.length != 3)
                return "Error: Invalid input format";
            double number1 = Double.parseDouble(parts[0].trim());
            String operand = parts[1].trim();
            double number2 = Double.parseDouble(parts[2].trim());
            switch (operand) {
                case "+":
                    System.out.println("Addition of numbers are  :");
                    double add = addition.add(number1, number2);
                    return String.valueOf(add);
                case "-":
                    System.out.println("Subtraction of numbers are  ");
                    double sub = subtraction.sub(number1, number2);
                    return String.valueOf(sub);
                case "/":
                    if (number2 == 0) {
                        return "Error: Cannot divide by zero";
                    }
                    System.out.println("Division of numbers are  ");
                    double div = division.division(number1, number2);
                    return String.valueOf(div);
                case "*":
                    System.out.println("Multiplication of numbers are  ");
                    return String.valueOf(multiplication.multi(number1, number2));
                default:
                    return "Error : Invalid Input";
            }
        } catch (NumberFormatException e) {
            return "Error: Invalid number format";
        }
    }
}

