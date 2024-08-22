package problems.array;

/*
Merge Two Arrays
        Objective: Write a method that merges two arrays into one.
        Requirements:
        The method mergeArrays(int[] array1, int[] array2) should return a new array containing
        all elements of array1 followed by all elements of array2 .
        Example Usage:
        mergeArrays(new int[]{1, 2}, new int[]{3, 4}) should return [1, 2, 3, 4] .
        mergeArrays(new int[]{10, 20}, new int[]{30}) should return [10, 20, 30] .

 */

import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoArray {

    public static void main(String[] args) {
        System.out.println("Enter first array of number");
        Scanner scanner = new Scanner(System.in);
        String firstStringInput = scanner.nextLine().trim();
        String[] firstArrayInput = firstStringInput.split(",");

        System.out.println("Enter second array of number");
        Scanner sc = new Scanner(System.in);
        String secondStringInput = sc.nextLine().trim();
        String[] secondArrayInput = secondStringInput.split(",");
        int[] firstArray = Utility.convertToIntegerArray(firstArrayInput);
        int[] secondArray = Utility.convertToIntegerArray(secondArrayInput);
        System.out.println("Merged array is " + Arrays.toString(mergeArrays(firstArray, secondArray)));

    }

    static int[] mergeArrays(int[] array1, int[] array2) {
        int[] mergeArray = new int[array1.length + array2.length];
//        int index = 0;
        int index = array1.length;
        for (int i = 0; i < array1.length; i++) {
            mergeArray[i] = array1[i];
        }
        for (int j = 0; j < array2.length; j++) {
            mergeArray[index] = array2[j];
            index++;
        }
        return mergeArray;

    }
}
