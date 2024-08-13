package problems.calculator_entity.operations;

public class Addition {
    public static String addition(double num1, double num2){
        double result =  num1 + num2;
        System.out.println("Addition of " + num1 + " and " + num2 + " is: " + result);
        return String.valueOf(result);
    }

}
