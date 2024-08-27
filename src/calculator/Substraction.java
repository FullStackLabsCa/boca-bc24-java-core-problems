package calculator;

public class Substraction {

    private final double result;

    public Substraction(double value1, double value2)
    {
        this.result = value1 - value2;
    }

    public String getResult() {

        return result+"";
    }

}
