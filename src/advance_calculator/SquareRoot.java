package advance_calculator;

public class SquareRoot extends Input {
    public SquareRoot(double num1, double num2) {
        super(num1, num2);
    }

    public double squareRoot(){
        double sqrt=0;
        if (num1<0){
            System.out.println("Square root of a negative number is not allowed.");
        }else {
            sqrt = Math.sqrt(num1);
        }
        return sqrt;
    }
}
