package problems.oldcalculator;

public class ArithmeticOperations {

    // Method for division
    public static double division(double operandOne, double operandTwo) {
        if (operandTwo == 0) {
//            throw new ArithmeticException("Division by zero is not allowed.");
            System.out.println("Division by zero is not allowed.");
            return Double.NaN;
        }
        return operandOne / operandTwo;
    }

    // Method for addition
    public static double addition(double operandOne, double operandTwo) {
        return operandOne + operandTwo;
    }

    // Method for subtraction
    public static double subtraction(double operandOne, double operandTwo) {
        return operandOne - operandTwo;
    }

    // Method for multiplication
    public static double multiplication(double operandOne, double operandTwo) {
        return operandOne * operandTwo;
    }

    // Method for power
    public static double power(double operandOne, double operandTwo) {
        return Math.pow(operandOne, operandTwo);
        // Math.pow: It is used for power calculations, which handles both positive and negative exponents efficiently.
    }

    // Method for square root
    public static double sqrt(double operandOne) {
        if (operandOne < 0) {
            throw new ArithmeticException("Square root of a negative number is not allowed.");
        }
        return Math.sqrt(operandOne);
    }
}

