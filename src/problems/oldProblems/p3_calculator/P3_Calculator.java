package problems.oldProblems.p3_calculator;

import java.util.Scanner;

public class P3_Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.print("Enter an expression (e.g., 3+2) or 'x' to exit: ");
            String input = scanner.nextLine().trim();

            if(input.equalsIgnoreCase("x")){
                System.out.println("Exiting the calculator. Goodbye!");
                break;
            }
            try{
                double result = calculate(input);
                System.out.println("Result: " + result);
            }catch (Exception e){
                System.out.println("Error: " + e);
            }
        }
        scanner.close();

    }

    private static double calculate(String input ) throws Exception{

        input = input.replaceAll("\\s+", "");
        char operator = ' ';
        int operatorIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operator = ch;
                operatorIndex = i;
                break;
            }
        }

        double operand1 = Double.parseDouble(input.substring(0, operatorIndex));
        double operand2 = Double.parseDouble(input.substring(operatorIndex + 1));

        switch (operator) {
            case '+':
                return new Addition().add(operand1, operand2);
            case '-':
                return new Subtraction().minus(operand1, operand2);
            case '*':
                return new Multiplication().multi(operand1, operand2);
            case '/':
                return new Division().divide(operand1, operand2);
            default:
                throw new Exception("Invalid operator.");
        }

    }


}
