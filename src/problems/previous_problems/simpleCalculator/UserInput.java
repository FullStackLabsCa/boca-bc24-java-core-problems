//package oldproblems.simpleCalculator;
//import java.util.Scanner;
//
//public class UserInput {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Calculator calculator = new Calculator();
//        String input;
//
//        while (true) {
//            System.out.print("Enter a valid expression: ");
//            input = scanner.next();
//            // Check for the exit condition
//            if (input.equalsIgnoreCase("x")) {
//                break;
//            }
//            // Validate the input
//            if (calculator.isValidExpression(input)) {
//                // Calculate the result
//                double result = calculator.calculate(input);
//                System.out.println("Result: " + result);
//            } else {
//                System.out.println("Invalid expression. Please try again.");
//            }
//        }
//    }
//}