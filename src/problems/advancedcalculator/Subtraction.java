package problems.advancedcalculator;

public class Subtraction {
    static String performSubtraction(int[] numbers) {
        int subtraction = numbers[0] - numbers[1];
        System.out.println("Subraction is " + subtraction);
        return String.valueOf(subtraction);
    }
}
