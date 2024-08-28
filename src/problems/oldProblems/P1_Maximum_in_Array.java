package problems.oldProblems;
//Problem 1: Find Maximum in Array
//Write a Java program to find the maximum value in an array of integers.

public class P1_Maximum_in_Array {

    public static void main(String[] args) {
        int i;
        int[] numbers = {90, 50, 40, 400, 300, 500, 200, 900};
        int max = numbers[0];

        for (i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println(max);
    }
}
