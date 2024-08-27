package problems.old_assignments.problems.problems;

public class SumOnlyOddNumberInArray {

    public static void main(String[] args) {
        double[] inputArray = {11, 2, 2.5, -1};
        double sum = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (!(inputArray[i] % 2 == 0)) {
                sum += inputArray[i];
            }
        }
        System.out.println("Sum of all even numbers are: " + sum);
    }
}
