package MathematicsProblem;

public class SumAll {
    public static void main(String[] args) {
            int[] number = {9, 3, 6, 3, 2}; // array number
            int sum = 0;
            for (int i = 0; i < number.length; i++) {
                sum += number[i];
            }
            System.out.println("Sum of All numbers are :" + sum);
    }
}
