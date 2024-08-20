package problems.calculator;

public class Substraction extends AbstractCalculator implements calculator {

    public final static double subtract(double[] operands){
        double result = operands[0];

        for (int i = 1; i < operands.length; i++) {
            result = result - operands[i];
        }
        return result;
    }

    @Override
    public double calculate(String expression) {
        boolean inputValidation = validateInput(expression);

        return 0;
    }
}
