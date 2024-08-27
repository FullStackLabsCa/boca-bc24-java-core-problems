package problems.array;

import java.util.Scanner;

public class MinMax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements you would like to add in an array: ");
        int n = Integer.parseInt(scanner.nextLine());

        int[] array = new int[n];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(scanner.nextLine());
        }

        int[] minMax = findMinMax(array);
        System.out.println("The minimum value in the array is: " + minMax[0]);
        System.out.println("The maximum value in the array is: " + minMax[1]);
    }

    public static int[] findMinMax(int[] numbers) {

        int max = numbers[0];
        int min = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return new int[]{min, max};
    }
}
