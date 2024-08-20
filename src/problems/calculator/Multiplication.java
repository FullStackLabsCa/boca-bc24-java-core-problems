package problems.calculator;

public class Multiplication extends AbstractCalculator implements calculator {


    public final static double multiply(double[] operands){
        double result = 1;

        for(double element : operands){
            result*=element;
        }
        return result;
    }

    @Override
    public double calculate(String expression) {
        boolean inputValidation = validateInput(expression);

        return 0;
    }
}
