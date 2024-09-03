package problems.advancedcalculator;

public class Addition {

//    static String performAddition(int[] numbers) {
//        int sum = 0;
//        for (Integer numb : numbers) {
//            sum += numb;
//        }
//        System.out.println("Addition is " + sum);
//        return String.valueOf(sum);
//    }

    static String performAddition(double[] numbers) {
        double sum = 0.0;
        for (Double numb : numbers) {
            sum += numb;
        }
        System.out.println("Addition is " + sum);
        return String.valueOf(sum);
    }
}
