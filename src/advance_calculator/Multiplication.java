package advance_calculator;

public class Multiplication extends Input {
    public Multiplication(double num1, double num2) {
        super(num1, num2);
    }

    public String multiplication(){
        double mul = num1*num2;
//        System.out.println("Result is "+mul);
        return String.valueOf(mul);
    }
}
