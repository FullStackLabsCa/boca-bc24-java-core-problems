package problems.string.advanced.calculator;

public class Division {
    private double result;

    public Division(double value1, double value2) {
        this.result = value1 / value2;
    }

    public String getResult() {
        return result+"";
    }
}
