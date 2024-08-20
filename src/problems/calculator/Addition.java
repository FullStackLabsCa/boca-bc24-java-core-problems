package problems.calculator;

public class Addition extends AbstractCalculator implements calculator {


    public static double add(double[] operands){
        double sum = 0 ;

        for(double element : operands){
            sum = sum+element;
        }
        return sum;
    }

    @Override
    public double calculate(String expression) {
        boolean inputValidation = validateInput(expression);

        return 0;
    }

}
