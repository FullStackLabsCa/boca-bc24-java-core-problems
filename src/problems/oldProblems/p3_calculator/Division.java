package problems.oldProblems.p3_calculator;

public class Division {

    public double divide(double a, double b) throws Exception{

        if (b==0){
            throw new Exception("Can not divide by zero");
        }
        return a / b;
    }
}
