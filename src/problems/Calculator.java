package problems;

import java.util.Scanner;

public class Calculator {
    public String calculate(String userInput) {

        try {
            if (userInput == null || userInput.trim().isEmpty()) return "Error: Input is empty or null";
            char operation = 0;
            String[] splittedStrings = userInput.split("\\+|\\-|\\*|\\/");
            int[] numbers = new int[splittedStrings.length];
            for (int i = 0; i < splittedStrings.length; i++) {
                if (splittedStrings[i].isBlank()) {
                    return "Error: Invalid input format";
                }
                numbers[i] = Integer.parseInt(splittedStrings[i].trim());
            }


            operation = getOperation(userInput, operation);

            switch (operation) {
                case '+' -> {
                    return performAddition(numbers);
                }
                case '-' -> {
                    return performSubtraction(numbers);
                }
                case '*' -> {
                    return performMultiplication(numbers);
                }
                case '/' -> {
                    return performDivision(numbers);
                }
            }
            return userInput;

        } catch (NumberFormatException e) {
            return "Error: Invalid number format";
        }

    }

    private static char getOperation(String userInput, char operation) {
        if (userInput.contains("+")) {
            operation = '+';
        } else if (userInput.contains("-")) {
            operation = '-';
        } else if (userInput.contains("*")) {
            operation = '*';
        } else if (userInput.contains("/")) {
            operation = '/';
        }
        return operation;
    }

    public static void main(String[] args) {
        while (true) {
            //Taking User Input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the operands with operation");
            String userInput = scanner.nextLine().trim();

            //Checking if user wants to exit
            if (userInput.equalsIgnoreCase("x")) {
                System.out.println("Exit From Calculator");
                return;
            }

            //Calulation part
            Calculator calculator = new Calculator();
            calculator.calculate(userInput);
        }
    }

    private static String performDivision(int[] numbers) {
        try {
            System.out.println(" Division " + Math.floor(numbers[0] / numbers[1]));
            return String.valueOf(Math.floor(numbers[0] / numbers[1]));
        } catch (ArithmeticException e) {
            return "Error: Cannot divide by zero";
        }
    }

    private static String performMultiplication(int[] numbers) {
        double multiplication = numbers[0] * numbers[1];
        System.out.println(" Multiplication " + multiplication);
        return String.valueOf(multiplication);
    }

    private static String performSubtraction(int[] numbers) {
        double subtraction = numbers[0] - numbers[1];
        System.out.println("Subraction is " + subtraction);
        return String.valueOf(subtraction);
    }

    private static String performAddition(int[] numbers) {
        double sum = 0;
        for (Integer numb : numbers) {
            sum += numb;
        }
        System.out.println("Addition is " + sum);
        return String.valueOf(sum);
    }
}
