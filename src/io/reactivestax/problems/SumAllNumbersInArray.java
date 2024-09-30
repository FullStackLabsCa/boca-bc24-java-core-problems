package io.reactivestax.problems;

public class SumAllNumbersInArray {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 5, 5};
        int sum=0;
        for (int number : numbers) {
            sum += number;
        }

        System.out.println("The Sum of All the elements of array is: "+sum);
    }
}
