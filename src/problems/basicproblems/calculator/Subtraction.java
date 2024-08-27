package calculator;

public class Subtraction {
    public static int performSubtraction(int[] operands) {
        int subtraction = 0;
        subtraction = operands[0] - operands[1];
        System.out.println("Subraction is " + subtraction);
        return subtraction;
    }

}

