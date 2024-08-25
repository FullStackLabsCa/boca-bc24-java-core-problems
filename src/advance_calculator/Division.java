package advance_calculator;

public class Division extends Input {
    public Division(double num1, double num2) {
        super(num1, num2);
    }

    public double division() {
        double div=0;
        if (num2!=0) {
            div = num1 / num2;
//            System.out.println("Result is " + div);
        }else {
            System.out.println("Division by zero is not allowed.");
        }
        return div;
    }
}