package problems.problems;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        runCalculator();
    }

    private static void runCalculator() {
        boolean keepRunning = true;

        while (keepRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter :");
            String input = scanner.nextLine();

            if ("x".equalsIgnoreCase(input)) {
                System.out.println("Exiting Calculator.");
                keepRunning = false;
            } else {
                processInput(input);
            }
        }
    }

    private static void processInput(String input) {
        char operator = 0;
        String[] parts = input.split("[+\\-*/]");
        int[] numbers = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i].trim());
        }

        for (char c : input.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operator = c;
                break;
            }
        }

        switch (operator) {
            case '+' -> {
                Addition.add(numbers);
                break;
            }
            case '-' -> {
                Subtraction.subtract(numbers);
                break;
            }
            case '*' -> {
                Multiplication.multiply(numbers);
                break;
            }
            case '/' -> {
                Division.divide(numbers);
                break;
            }
            default -> System.out.println("Invalid operator.");
        }
    }

    private static class Addition {
        static void add(int[] numbers) {
            int result = 0;
            for (int number : numbers) {
                result += number;
            }
            System.out.println("Result: " + result);
        }
    }

    private static class Subtraction {
        static void subtract(int[] numbers) {
            if (numbers.length == 0) {
                System.out.println("No numbers provided.");
                return;
            }
            int result = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                result -= numbers[i];
            }
            System.out.println("Result: " + result);
        }
    }

    private static class Multiplication {
        static void multiply(int[] numbers) {
            if (numbers.length == 0) {
                System.out.println("No numbers provided.");
                return;
            }
            int result = 1;
            for (int number : numbers) {
                result *= number;
            }
            System.out.println("Result: " + result);
        }
    }

    private static class Division {
        static void divide(int[] numbers) {
            if (numbers.length == 0) {
                System.out.println("No numbers provided.");
                return;
            }
            int result = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] == 0) {
                    System.out.println("Division by zero error.");
                    return;
                }
                result /= numbers[i];
            }
            System.out.println("Result: " + result);
        }
    }
}
