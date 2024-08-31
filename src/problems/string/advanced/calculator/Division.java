package problems.string.advanced.calculator;

public class Division {
    private double result;
    double num1;
    double num2;

    public Division(double value1, double value2) {
        num1 = value1;
        num2 = value2;
        this.result = value1 / value2;
    }

    public String getResult() {
        if (num2 == 0) {
            return String.valueOf(Double.NaN);
        }
        return result + "";
    }
}
