package advance_calculator;

public class Subtraction extends Input {
    public Subtraction( double num1, double num2) {
        super(num1, num2);
    }

    @Override
    public String toString() {
        return "Subtraction{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                "} " + super.toString();
    }
    public String subtraction(){
        double sub;
        sub=num1-num2;
//        System.out.println("Result is "+sub);
        return String.valueOf(sub);
    }
}
