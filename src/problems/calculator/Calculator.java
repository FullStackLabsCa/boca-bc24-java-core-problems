package problems.calculator;

import java.util.Scanner;

import static java.lang.Double.parseDouble;

@SuppressWarnings("java:S106")
public class Calculator {
    public static String calculate(String o) {
        if (o == null || o.isEmpty()) {
            System.out.println("Error: Input is empty or null");
            return "Error: Input is empty or null";
        }
        String[] parts;
        parts = o.split("(?<=[-+*/])|(?=[-+*/])");
        if (parts[0].equals("abc ")) {
            System.out.println("Error: Invalid number format");
            return "Error: Invalid number format";
        }

        if (parts[2].equals(" ")) {
            System.out.println("Error: Invalid input format");
            return "Error: Invalid input format";
        }

        double number1 = parseDouble(parts[0]);
        String operand = parts[1];
        double number2 = parseDouble(parts[2]);
        String result = "";

        if (number2 == 0) {
            System.out.println("Error: Cannot divide by zero");
            return "Error: Cannot divide by zero";
        }

        switch (operand) {
            case "+":
                Addition add = new Addition(number1, number2);
                result = add.getResult();
                break;
            case "-":
                Subtraction sub = new Subtraction(number1, number2);
                result = sub.getResult();
                break;
            case "*":
                Multiplication multiply = new Multiplication(number1, number2);
                result = multiply.getResult();
                break;
            case "/":
                Division divide = new Division(number1, number2);
                result = divide.getResult();
                break;
            default:
                System.out.println("Only Addition, Subtraction, Multiplication, Division operations are allowed");
        }
        System.out.println("Result of two operands: " + result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Simple Calculator");

        while (true) {
            System.out.println("Please enter a values to calculate");
            Scanner value = new Scanner(System.in);
            String inputValue = value.nextLine();
            if (inputValue.contains("exit")) {
                System.out.println("Exiting from calculator");
                break;
            }
            calculate(inputValue);
        }
    }
}
