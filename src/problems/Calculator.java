package problems;

import java.util.Arrays;
import java.util.Scanner;
public class Calculator {
    double a, b;
    char operator = '\0';

    public static void main(String[] args) {

        Scanner obj = new Scanner(System.in);

        boolean exit = true;
        while (exit) {
            System.out.println("Enter the operation you want to perform");
            String input = obj.nextLine();
            if (input.equalsIgnoreCase("x")) {
                exit = false;
                break;
            } else {
                System.out.println(calculate(input));
            }
        }
    }

        public static String calculate (String input){
            if (input == null || input.trim().isEmpty()) {
                return "Error: Input is empty or null";
            }

            Calculator calculator = new Calculator();
            try {
                char op = calculator.getOperator(input);
                String[] operands = input.split("[+\\-*/]");
                if (operands.length < 2 || operands[0].trim().isEmpty() || operands[1].trim().isEmpty()) {
                    return "Error: Invalid input format";
                }

                double num1 = Double.parseDouble(operands[0].trim());
                double num2 = Double.parseDouble(operands[1].trim());
                return calculator.getAnswer(num1, num2, op);
            } catch (NumberFormatException e) {
                return "Error: Invalid number format";
            }
        }
    public char getOperator(String input) {

        for (char c : input.toCharArray()) {
            if (c == '+' || c == '*' || c == '/' || c == '-') {
                operator = c;
            }
        }
        return operator;
    }

    public String getOperand1(String input) {
        String[] operands = input.split("[+\\-*/]");
        try {
            a = Double.parseDouble(operands[0]);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
           return "Error: Invalid number format";
        }
        return String.valueOf(a);
    }

    public String getOperand2(String input) {
        String[] operands = input.split("[+\\-*/]");

            b = Double.parseDouble(operands[1]);
            return String.valueOf(b);

    }

    public String getAnswer(double num1,double num2,char op){
        double ans = 0;
        String divideAns= "";
        switch (op) {
            case '+' -> ans = num1 + num2;
            case '-' -> ans = num1 - num2;
            case '*' -> ans = num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero");
                    return divideAns+"Error: Cannot divide by zero";
                } else {
                    ans = num1 / num2;
                }
            }
            default -> System.out.println("Invalid");
        }
        return String.valueOf(ans);
    }

}

