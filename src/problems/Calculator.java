package problems;


import java.util.Scanner;

import static java.lang.Double.isNaN;

public class Calculator {

    double a, b;
    char operator = '\0';
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            System.out.println("Enter the operation you want to perform");
            String input = obj.nextLine().trim().replaceAll("\\s","");
            if (input.equals("x")) {
                exit = false;
                break;
            }
            else {

                System.out.println(calculate(input));
            }
        }
    }



    public static String calculate(String input){
        if(input == null || input.trim().isEmpty()){
            return "Error: Input is empty or null";
        }
        Calculator calculator = new Calculator();
        double num1 = Double.parseDouble(calculator.getOperand1(input));
        double num2 = Double.parseDouble(calculator.getOperand2(input));
        char op = calculator.getOperator(input);
//        System.out.println(calculator.getAnswer(num1, num2, op));
        return calculator.getAnswer(num1,num2,op);
    }

    public char getOperator(String input) {

        for (char c : input.toCharArray()) {
            if (c == '+' || c == '*' || c == '/' || c == '-') {
                operator = c;
            }
        }
        return operator;
    }

    public String  getOperand1(String input) {
        String[] operands = input.split("[+\\-*/]");
        try {

            a = Double.parseDouble(operands[0]);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("First number is not valid");
        }
        return String.valueOf(a);
    }
    public String getOperand2(String input) {
        String[] operands = input.split("[+\\-*/]");
        try {
            b = Double.parseDouble(operands[1]);

        } catch (NumberFormatException  | ArrayIndexOutOfBoundsException e) {
            System.out.println("Second number is not valid");
        }
        return String.valueOf(b);
    }

    public String getAnswer(double num1,double num2,char op){
        double ans = 0;
//        if(isNaN(num1)){
//            return "Error: Invalid number format";
//        }
        if(num1 == 0 || num2 == 0){
            return "Error: Cannot divide by zero";
        }
        switch (op) {
            case '+' -> ans = num1 + num2;
            case '-' -> ans = num1 - num2;
            case '*' -> ans = num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    System.out.println("Cannot divide by 0");
                    ans = 0;
                } else {
                    ans = num1 / num2;
                }
            }
            default -> System.out.println("Invalid");
        }
        return String.valueOf(ans);
    }

}

