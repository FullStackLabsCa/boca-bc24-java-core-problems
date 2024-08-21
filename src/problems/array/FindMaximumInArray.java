package problems.array;

import java.util.Scanner;

public class FindMaximumInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements you would like to add in an array: ");
        String n = scanner.nextLine();

        int[] array = new int[Integer.parseInt(n)];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < Integer.parseInt(n); i++) {
            array[i] = Integer.parseInt(scanner.nextLine());
        }

        int maxValue = findMax(array);
        System.out.println("The maximum value in the array is: " + maxValue);
    }

    public static int findMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return Integer.MIN_VALUE;
        }

        int max = numbers[0];
        for (int j = 0; j < numbers.length; j++) {
            if (numbers[j] > max) {
                max = numbers[j];
            }
        }
        return max;
    }
}