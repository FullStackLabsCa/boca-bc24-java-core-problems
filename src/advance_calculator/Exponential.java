package advance_calculator;

public class Exponential extends Input {
    public Exponential(double num1, double num2) {
        super(num1, num2);
    }

    public double exponential(){
        double exp=1;
        if (num2<0){
            System.out.println("Operation not supported.");
        }else {
            for (int i = 1; i <= num2; i++) {
                exp = num1 * exp;
            }
        }
        return exp;
    }
}
