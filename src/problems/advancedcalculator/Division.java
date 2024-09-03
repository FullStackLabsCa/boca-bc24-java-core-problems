package problems.advancedcalculator;

public class Division {

    static String performDivision(double[] numbers) {

        try {
            if(numbers[1] == 0) {
                return String.valueOf(Double.NaN);
            }
            System.out.println(" Division " +  (int) Math.round((double) numbers[0] / numbers[1]));
            return String.valueOf( (int) Math.round((double) numbers[0] / numbers[1]));
        } catch (ArithmeticException e) {
            return String.valueOf(Double.NaN);
        }
    }
}
