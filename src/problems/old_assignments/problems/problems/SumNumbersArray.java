package problems.old_assignments.problems.problems;

public class SumNumbersArray {
    public static void main(String[] args) {
        double[] inputArray = {12, 34, 64, -2, -2.5, 0.5};
    double sum = 0;
        for (int i = 0; i < inputArray.length-1; i++) {
           sum+= inputArray[i];
        }
        System.out.println("Sum of all numbers in the Array is: " + sum);
    }
}