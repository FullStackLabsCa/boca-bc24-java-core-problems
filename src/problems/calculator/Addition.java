package problems.calculator;

public class Addition {
    private double result;

    public Addition(double value1, double value2) {
        this.result = value1 + value2;
    }

    public String getResult() {
        return result+"";
    }
}
