package problems.advancedcalculator;

public class Multiplication {

    static String performMultiplication(int[] numbers) {
        int multiplication = numbers[0] * numbers[1];
        System.out.println(" Multiplication " + multiplication);
        return String.valueOf(multiplication);
    }
}
