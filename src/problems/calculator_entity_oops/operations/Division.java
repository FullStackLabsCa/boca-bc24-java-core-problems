package problems.calculator_entity_oops.operations;

public class Division {
    public static String division(double num1, double num2) {
        if (num2 != 0) {
            double result = num1 / num2;
            System.out.println("Division of " + num1 + " and " + num2 + " is: " + result);
            return String.valueOf(result);
        } else throw new ArithmeticException("Zero is Acceptable in Denominator ");
    }
}
