package problems.calculator_entity.operations;

public class Subtraction {
    public static String subtraction(double num1, double num2){
        double result = num1 - num2;
        System.out.println("Subtraction of " + num1 + " and " + num2 + " is: " + result);
        return String.valueOf(result);
    }

}
