package problems.string.advanced.calculator;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

@SuppressWarnings("java:S106")
public class Calculator {
    public static void calculate(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Error: Input is empty or null");
            return;
        }
        LinkedHashMap<Number, Object> checkDivisionMap = new LinkedHashMap<>();
        String[] parts;
        parts = str.split(" ");
        int index = 0;

        //adding tuples
        for (int i = 0; i < parts.length - 1; i = i + 2) {
            String tuple = "";
            tuple = parts[i] + parts[i + 1] + parts[i + 2];
            checkDivisionMap.put(index, tuple);
            index++;
        }

        checkDivisionMap.forEach((k, v) -> {
            String value = v.toString();
            String operand = "/";
            boolean containSlash = value.contains("/");
            if (containSlash) {
                String[] part = value.split(operand);
                if (Double.parseDouble(part[1]) == 0) {
                    System.out.println("Error: Cannot divide by zero");
                } else if (part[0].equals(" ") || part[1].equals(" ")) {
                    System.out.println("Error: Invalid input format");
                } else {
                    performOperation(Double.parseDouble(part[0]), operand, Double.parseDouble(part[1]));
                }
            }
        });
    }

    public static void performOperation(double number1, String operand, double number2) {
        String result = "";
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
    }

    public static void clearMemory() {
    }

    public static void main(String[] args) {
        System.out.println("Advanced Calculator");

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
