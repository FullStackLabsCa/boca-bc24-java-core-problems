package problems.calc;

public class Division {
    public static float div(float a, float b)
    {
        if (b== 0)
        {
            throw new ArithmeticException("Dividng by zero.");
        }
        else {
            return a / b;
        }
    }
}
