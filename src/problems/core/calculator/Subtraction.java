package problems.core.calculator;

public class Subtraction {
    private double result;

    public Subtraction(double value1, double value2) {
        this.result = value1 - value2;
    }

    public String getResult() {
        return result+"";
    }
}
