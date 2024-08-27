package calculator;

//Create a simple calculator that can perform basic arithmetic operations (addition, subtraction, multiplication, division).
//
//        Requirements:
//
//        Use classes to model the calculator.
//        Use control flow statements to handle invalid operations (e.g., division by zero).

// hit enter to calculate

// hit x to exit


//they should have ability to get multiple operations untill exit.

import java.util.Scanner;

public class Calculator {

    public static int Calculate() {
//
       int counter = 0;

       while (counter == 0) {
           Scanner sc = new Scanner(System.in);  // Create a Scanner object
           System.out.println("Enter the operands with operation");
           String userInput = sc.nextLine();

           if (userInput.equalsIgnoreCase("x")) {
               System.out.println("Exit From Calculator");
               counter = 1;
               return 0;
           }
           callCalculatorOperations(userInput);

       }
        return 1;
    }

    private static void callCalculatorOperations(String userInput) {
        char operation = 0;
        String[] numberStrs = userInput.split("\\+|\\-|\\*|\\/");
        int[] numbers = new int[numberStrs.length];
        for (int i = 0; i < numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }

        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == '+' || userInput.charAt(i) == '-' || userInput.charAt(i) == '/' || userInput.charAt(i) == '*') {
                operation = userInput.charAt(i);
                System.out.println("op" + operation);
            }

            switch (operation) {
                case '+' -> Addition.performAddition(numbers);
                case '-' -> Subtraction.performSubtraction(numbers);
                case '*' -> Multiplication.performMultiplication(numbers);
                case '/' -> Division.performDivision(numbers);
            }
        }
    }
}
