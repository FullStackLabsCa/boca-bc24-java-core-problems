package problems;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String inputStr;
        boolean validInput = false;
        do {
            System.out.println("Please Enter Operands and Operator (Ex: 4+3) : ");
            inputStr = myObj.nextLine();
            //System.out.println("Input is : " + inputStr);
            if (!(inputStr.matches("(\\p{Alnum})?\s*([+\\-*/])?\s*(\\p{Alnum})?"))) {
                if (inputStr.equalsIgnoreCase("x")) {
                    return;
                } else {
                    System.out.println("Incorrect input ");
                }
            } else {
                validInput = true;
            }
        } while (!validInput);
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate(inputStr));
    }

    public String calculate(String inputStr) {
        if ((inputStr == null)) {
            return ("Error: Input is empty or null");
        } else if (inputStr.equals("")) {
            return ("Error: Input is empty or null");
        } else if (inputStr.equalsIgnoreCase("x")) {
            return ("Exiting program");
        }
        if (!(inputStr.matches("\\d+(\\.\\d*)?\s*([+\\-*/])?\s*\\d+(\\.\\d*)?"))) {
            if (inputStr.matches("\\d+(\\.\\d*)?\s*([+\\-*/])?\s*") || inputStr.matches("\s*([+\\-*/])?\s*\\d+(\\.\\d*)?")) {
                return ("Error: Invalid input format");
            }
            return ("Error: Invalid number format");
        } else {
            Double result;
            String[] opr = inputStr.split("[-+*/]");
            double operand1 = Double.parseDouble(opr[0]);
            double operand2 = Double.parseDouble(opr[1]);
            String operator = inputStr.replaceAll("\s|[0-9]|\\.", "");
            switch (operator) {
                case "+":
                    result = CalculatorOperations.addition(operand1, operand2);
                    break;
                case "-":
                    result = CalculatorOperations.subtraction(operand1, operand2);
                    break;
                case "*":
                    result = CalculatorOperations.multiplication(operand1, operand2);
                    break;
                case "/":
                    if (operand2 == 0) {
                        return ("Error: Cannot divide by zero");
                    }
                    result = CalculatorOperations.division(operand1, operand2);
                    break;
                default:
                    throw new IllegalArgumentException("Operator is not Supported.");
            }
            return (String.valueOf(result));
        }
    }
}


