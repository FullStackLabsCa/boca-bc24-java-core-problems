package io.reacticestax.problems;
import java.util.Scanner;


class Addition {
    public  double add(double operand1, double operand2) {
        return operand1 + operand2;

    }

}

class Subtraction {
    public  double sub(double operand1, double operand2) {
        return operand1 - operand2;

    }
}

class Multiplication {
    public  double mul(double operand1, double operand2) {
        return operand1 * operand2;
    }
}

class Division {
    public  double div(double operand1, double operand2) {
        if (operand2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return operand1 / operand2;
    }
}





public class Calculator {


        String operation = "";

        public String  calculate (String operation){

            do {
                Scanner scanner = new Scanner(System.in);

                // Assuming these classes are correctly defined elsewhere
                System.out.println("Enter an operation (e.g., 234+567) or 'x' to exit:");

                operation = scanner.nextLine();

                // Check if the user wants to exit
                if (operation.equalsIgnoreCase("x")) {
                    break;
                }

                // Regular expression to validate the operation format
                String regex = "\\d+[.]?\\d+[+\\-*/]\\d+[.]?\\d+$";

                if (operation.matches(regex) || operation.matches("\\d+[+\\-*\\/]\\d+$")) {
                    // Split the string into operands
                    String[] arrOfStr = operation.split("[+\\-*/]");
                    double operand1 = Double.parseDouble(arrOfStr[0]);
                    double operand2 = Double.parseDouble(arrOfStr[1]);

                    // Extract the operator by searching the original string
                    char operator = ' ';
                    for (char ch : operation.toCharArray()) {
                        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                            operator = ch;
                            break;
                        }
                    }

                    System.out.println("Operand 1: " + operand1 + " Operand 2: " + operand2);
                    System.out.println("Operator: " + operator);

                    // Use switch-case with the operator
                    switch (operator) {
                        case '+':
                            Addition a = new Addition();
                            System.out.println("Result: " + a.add(operand1, operand2));
                            break;
                        case '-':
                            Subtraction b = new Subtraction();
                            System.out.println("Result: " + b.sub(operand1, operand2));
                            break;
                        case '*':
                            Multiplication c = new Multiplication();
                            System.out.println("Result: " + c.mul(operand1, operand2));
                            break;
                        case '/':
                            Division d = new Division();
                            try {
                                System.out.println("Result: " + d.div(operand1, operand2));
                            } catch (ArithmeticException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        default:
                            System.out.println("Unexpected operator.");
                            break;
                    }
                } else {
                    System.out.println("Invalid operation format. Please try again.");
                }

                // Ask user if they want to exit or continue
                System.out.println("Press 'x' to exit or any other key to continue:");
//            exitChoice = scanner.nextLine().trim().toLowerCase().charAt(0);

            } while (operation.toLowerCase() != "x");

            System.out.println("Exiting the calculator.");
//        scanner.close();

            return operation;
        }


    }




