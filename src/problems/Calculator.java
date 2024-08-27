package problems;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        String abc = scanner.next();
        System.out.println(calculate(abc));
    }

    public static String calculate(String number) {

        if ((number == "") || (number == null)) {
            return "Error: Input is empty or null";
        }

        String abc = number.replaceAll("\\s+", "");

        String op1;
        String op2;
        char op3;
        String[] arrOfStr = abc.split("[+\\-*/]");
        if (arrOfStr.length < 2) {
            return "Error: Invalid input format";
        }

        op1 = arrOfStr[0];
        op2 = arrOfStr[1];
        op3 = abc.charAt(op1.length());
        String result;
        try {
            switch (op3) {
                case '+' -> result = add(op1, op2);
                case '*' -> result = mul(op1, op2);
                case '-' -> result = sub(op1, op2);
                case '/' -> result = dev(op1, op2);
                default -> result = "Invalid Input";
            }
        } catch (NumberFormatException e) {
            return "Error: Invalid number format";
        }
        return result;
    }

    public static String add(String op1, String op2) {
        double num1 = Double.parseDouble(op1);
        double num2 = Double.parseDouble(op2);
        double sum = num2 + num1;
        String result = sum + "";
        return result;
    }

    public static String mul(String op1, String op2) {
        double num1 = Double.parseDouble(op1);
        double num2 = Double.parseDouble(op2);
        double sum = num2 * num1;
        String result = sum + "";
        return result;
    }

    public static String sub(String op1, String op2) {
        double num1 = Double.parseDouble(op1);
        double num2 = Double.parseDouble(op2);
        double sum = num1 - num2;
        String result = sum + "";
        return result;
    }

    public static String dev(String op1, String op2) {
        double num1 = Double.parseDouble(op1);
        double num2 = Double.parseDouble(op2);
        if (num2 == 0) {
            return "Error: Cannot divide by zero";
        } else {
            double sum = num1 / num2;
            String result = sum + "";
            return result;
        }
    }
}