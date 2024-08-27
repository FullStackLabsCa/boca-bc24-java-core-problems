package calculator;

public class Multiplication {
    private final double result;

    public Multiplication(double value1, double value2)
    {
        this.result = value1 * value2;
    }

    public String getResult() {

        return result+"";
    }
}
