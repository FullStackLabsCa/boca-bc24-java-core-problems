package problems.array;

/*
Find the Smallest and Largest Elements in an Array
        Objective: Write a method that finds both the smallest and largest elements in an array.
        Requirements:
        The method findMinMax(int[] array) should return an array where the first element is the
        smallest and the second element is the largest.
        Example Usage:
        findMinMax(new int[]{1, 2, 3, 4, 5}) should return [1, 5] .
        findMinMax(new int[]{10, -3, 7, 2}) should return [-3, 10] .
        Hints:
        Iterate through the array, keeping track of the smallest and largest values encountered.

 */

import problems.advancedcalculator.Utility;

import java.util.Arrays;
import java.util.Scanner;

public class SmallestandLargestElementInArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine().trim();
        String[] split = stringInput.split(",");
        int[] ints = Utility.convertToIntegerArray(split);
        System.out.println(Arrays.toString(findMinMax(ints)));

    }

    static int[] findMinMax(int[] array) {
        int[] minMax = new int[2];
        int min = array[0];
        int max = array[array.length - 1];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (max < array[i]) max = array[i];
        }

        minMax[0] = min;
        minMax[1] = max;
        return minMax;
    }
}
