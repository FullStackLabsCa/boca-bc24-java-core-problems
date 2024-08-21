package problems.calculator_Entity;

public class Multiplication extends Input {
    public Multiplication(float num1, float num2) {
        super(num1, num2);
    }

    public String multiplication(){
        float mul = num1*num2;
        System.out.println("Result is "+mul);
        return String.valueOf(mul);
    }
}
