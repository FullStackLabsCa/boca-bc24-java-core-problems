package io.reacticestax.problems;

public class OddNumberSum {

    public static void main(String[] args) {

        int number = 1;

        int sum = 0;
        int count = 0;

        while (count < 50) {
            if (number % 2 != 0) {
                sum = number + sum;
                count++;
            }
            number++;   // incrementing number each time number is not odd
        }
        System.out.println("sum is : " + sum);
    }
}
