package problems.array;

/*
Objective: Write a method that checks if an array is sorted in ascending order.
        Requirements:
        The method isSorted(int[] array) should return true if the array is sorted and false
        otherwise.
        Example Usage:
        isSorted(new int[]{1, 2, 3, 4}) should return true .
        isSorted(new int[]{4, 3, 2, 1}) should return false .
        Hints:
        Compare each element with the next one to ensure the order is non-decreasing.

 */

import java.util.Scanner;

public class CheckIfArraySorted {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine().trim();
        String[] split = stringInput.split(",");



        int[] ints = Utility.convertToIntegerArray(split);
        boolean sorted = isSorted(ints);
        System.out.println("Array is Sorted: " + sorted);
    }

    static boolean isSorted(int[] array) {
        int min = array[0];
        for (int arr : array) {
            if (min > arr) {
                min = arr;
                return false;
            }
        }
        return true;

    }

}
