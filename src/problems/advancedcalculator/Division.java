package problems.advancedcalculator;

public class Division {

    static String performDivision(int[] numbers) {
        try {
            System.out.println(" Division " + Math.floor(numbers[0] / numbers[1]));
            return String.valueOf(Math.floor(numbers[0] / numbers[1]));
        } catch (ArithmeticException e) {
            return "Error: Cannot divide by zero";
        }
    }
}
