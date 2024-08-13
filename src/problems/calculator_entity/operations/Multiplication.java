package problems.calculator_entity.operations;

public class Multiplication {
    public static String multiplication(double num1, double num2){
        double result = num1 * num2;
        System.out.println("Multiplication of " + num1 + " and " + num2 + " is: " + result);
        return String.valueOf(result);
    }

}
