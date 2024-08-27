package calculator;

public class Division {
    public static void performDivision(int[] operands) {
        double division = 0;
        if(operands[1] == 0) {
            System.out.println("Cannot be divisible by 0");

        }
        division = Math.floor(operands[0] / operands[1]);
        System.out.println(" Division " + division);
    }
}
