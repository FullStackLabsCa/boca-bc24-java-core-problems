package problems.calculator;

public class ArithmeticOperations {
    public static double division(double oerrandOne, double operandTwo) {
        if (operandTwo == 0) {
            System.out.println("Division by zero not allowed");
        }
        return oerrandOne / operandTwo;

    }

    public static double additioon(double oerrandOne, double operandTwo) {

        return oerrandOne + operandTwo;
    }

    public static double subtraction(double oerrandOne, double operandTwo) {

        return oerrandOne - operandTwo;
    }

    public static double multiplication(double oerrandOne, double operandTwo) {

        return oerrandOne * operandTwo;
    }

    public static double power(double oerrandOne, double operandTwo) {
        double answer=oerrandOne;
        if (operandTwo < 0) {
            System.out.println("Operation not supported.");
        }

        for(int i=1; i< (int) operandTwo;i++){
            answer = answer*oerrandOne;
        }
        return answer;
    }

    public static double sqrt(double oerrandOne) {
        if (oerrandOne < 0) {
            System.out.println("Square root of a negative number is not allowed");
        }
        return Math.sqrt(oerrandOne);
    }
}
