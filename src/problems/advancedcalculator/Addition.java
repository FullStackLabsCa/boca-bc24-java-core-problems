package problems.advancedcalculator;

public class Addition {

    static String performAddition(int[] numbers) {
        int sum = 0;
        for (Integer numb : numbers) {
            sum += numb;
        }
        System.out.println("Addition is " + sum);
        return String.valueOf(sum);
    }
}
