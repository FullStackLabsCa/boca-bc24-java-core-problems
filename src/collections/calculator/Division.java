package collections.calculator;

public class Division{

    public static double divide(double numerator, double denominator) {
        //Divide Validation
        if(denominator == 0){
            System.out.println("Illegal Argument!!! Denominator cannot be ZERO.");
            System.exit(-1);
        }

        return (numerator / denominator);
    }

//    @Override
//    public double calculate(String expression) {
//        boolean inputValidation = validateInput(expression);
//
//        return 0;
//    }
}
