package problems;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string (ex. 2 + 5): ");
        String inputString  = scanner.nextLine();

        Calculator calculator = new Calculator();
        String result  = calculator.calculate(inputString);
        System.out.println(result);


    }


    public String calculate(String input) {

        if(input == null || input.trim().isEmpty()){
            return "Error: Input is empty or null";
        }

        String[] parts = input.split(" ");
        if (parts.length !=  3){
            return "Error: Invalid input format";
        }

        String operator = parts[1];
        double num1,num2;

        try{
            num1 = Double.parseDouble(parts[0]);
            num2 = Double.parseDouble(parts[2]);
        }catch (NumberFormatException e){
            return "Error: Invalid number format";
        }

        switch (operator){
            case "+":
                return String.valueOf(num1 + num2);
            case "-":
                return String.valueOf(num1 - num2);
            case "*":
                return String.valueOf(num1 * num2);
            case "/":

                if(num2==0){
                    return "Error: Cannot divide by zero";
                }
                return String.valueOf(num1 / num2);

            default:
                return "Invalid operator";

        }

    }
}
