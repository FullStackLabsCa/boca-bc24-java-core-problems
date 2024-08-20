package problems;

import java.util.Scanner;

public class FindMaximumInArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        int n = input.nextInt();

        double[] numbers = new double[n];

        System.out.println("Please enter number:");
        numbers[0] = input.nextDouble();
        double max = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            System.out.println("Please enter number:");
            numbers[i] = input.nextDouble();

            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        System.out.printf("The maximum value is: %.2f%n", max);
    }

    public static int findMax(int[] numbers) {

        if (numbers == null || numbers.length == 0) {
            return Integer.MIN_VALUE;
        }

        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
}
