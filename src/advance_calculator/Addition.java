package advance_calculator;

public class Addition extends Input {
    public Addition(double num1, double num2)
    {
        super(num1, num2);
    }
    public double addition(){
        double add = num1+num2;
//        System.out.println("Result is "+add);
        return add;
    }
}
