package Problem3Calcualtor;

public class Division {
    public static double div(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return num1 / num2;
    }
    }

