package problems.array;

import problems.advancedcalculator.Utility;

import java.util.Arrays;
import java.util.Scanner;

/*
Shift Elements in an Array
        Objective: Write a method that shifts all elements of an array to the right by a specified number of
        positions.
        Requirements:
        The method shiftArray(int[] array, int positions) should shift the array elements and
        handle wrap-around.
        Example Usage:
        shiftArray(new int[]{1, 2, 3, 4}, 2) should modify the array to [3, 4, 1, 2] .
        shiftArray(new int[]{10, 20, 30}, 1) should modify the array to [30, 10, 20] .
        Hints:
        Consider how to handle the wrap-around when shifting elements.

 */
public class ShiftElementsInArray {

    public static void main(String[] args) {
        System.out.println("Enter array to Shift Array");
        Scanner scanner = new Scanner(System.in);
        String[] splittedInput = scanner.nextLine().trim().split(",");
        int[] ints = Utility.convertToIntegerArray(splittedInput);

        System.out.println("Enter position to shift the array");
        Scanner sc = new Scanner(System.in);
        int position = sc.nextInt();
        System.out.println(Arrays.toString(shiftArray(ints, position)));
    }

    static int[] shiftArray(int[] array, int positions) {
        int[] shiftedArray = new int[array.length];
        int j = 0;
        int indexPosition = array.length - positions;
        for (int i = 0; i < array.length; i++) {
            shiftedArray[j] = array[indexPosition];
            if (j < array.length - 1) {
                j++;
            }
            indexPosition++;
            if (indexPosition > array.length - 1) {
                indexPosition = 0;
            }
        }
        return shiftedArray;

    }
}
