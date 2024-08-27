package problems.old_assignments.problems.problems;

public class Division {
    public void division(double num1, double num2){
        if(num2 != 0) {
            double result = num1 / num2;
            System.out.println("Division of " + num1 + " and " + num2 + " = " + result);
        }else throw new ArithmeticException("Zero is Acceptable in Denominator ");
    }

}
