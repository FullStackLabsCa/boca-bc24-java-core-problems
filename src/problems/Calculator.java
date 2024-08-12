package problems;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression: ");
        String input = scanner.nextLine();


        String result = calculate(input);
        System.out.println("Result: " + result);
    }

    public static boolean checkExpression(String expr) {
        return expr.matches("^\\s*\\d+\\s*[+\\-*/]\\s*\\d+\\s*$");

    }

    public static String calculate(String s) {
        float result = 0;
        if(s == null || s.equalsIgnoreCase("")){
            return "Error: Input is empty or null";
        }
        else if  (!checkExpression(s)){
            String[] parts = s.split("\\s*[+\\-*/]\\s*");

            if (parts.length!=2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()){
                return "Error: Invalid input format";
            }
            else {
                return "Error: Invalid number format";
            }
        }else {
            String[] parts = s.split(" ");

            float num1 = Float.parseFloat(parts[0]);
            float num2 = Float.parseFloat(parts[2]);
            char operator = parts[1].charAt(0);

            switch (operator) {
                case '+':
                    result = Addition.add(num1, num2);
                    break;
                case '-':
                    result = Subtraction.sub(num1, num2);
                    break;
                case '*':
                    result = Multiplication.mul(num1, num2);
                    break;
                case '/':
                    if(num2 == 0){
                        return "Error: Cannot divide by zero";
                    }else {
                        result = Division.div(num1, num2);
                    }
                    break;
                default:
                    return "Invalid operator";
            }
        }
        return String.valueOf(result);
    }
}