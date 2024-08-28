package testproblems;

import java.util.Scanner;

public class Calculator {
    public static boolean divideByZero(double num) {
        return num == 0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        String str = scanner.nextLine();
        String result = calculator.calculate(str);
        System.out.println(result);
    }

    public String calculate(String s) {
        String result = "";
        if (s == null) {
            result = "Error: Input is empty or null";
        }
        else if (s.length() == 0) {
            result = "Error: Input is empty or null";
        }
        else if (s.matches("^[\\w\\d]+(\\.\\d+)?\s*[+\\-*/]\s*[\\w\\d]+(\\.\\d+)?$")) {
            String[] numbers = s.split("[+\\-*/]");
            char operator = s.charAt(numbers[0].length());

            String numberRegex = "\\d+(\\.\\d+)?";

            if (!numbers[0].trim().matches(numberRegex) || !numbers[1].trim().matches(numberRegex)) {
                result = "Error: Invalid number format";
                return result;
            }
            double number1 = Double.parseDouble(numbers[0]);
            double number2 = Double.parseDouble(numbers[1]);

            switch (operator) {
                case '+':
                    result = String.valueOf(number1 + number2);
                    break;
                case '-':
                    result = String.valueOf(number1 - number2);
                    break;
                case '*':
                    result = String.valueOf(number1 * number2);
                    break;
                case '/':
                    boolean isDivideByZero = divideByZero(number2);
                    if (isDivideByZero) {
                        result = "Error: Cannot divide by zero";
                        break;
                    }
                    result = String.valueOf(number1 / number2);
                    break;
                default:
                    System.out.println("Invalid Operation");
                    break;
            }

        }else {
            result = "Error: Invalid input format";
        }
        return result;
    }
}
