package problems.array;

/*
Find the Duplicate Elements in an Array
        Objective: Write a method that finds and returns any duplicate elements in an array.
        Requirements:
        The method findDuplicates(int[] array) should return an array of duplicates found in the
        input array.
        Example Usage:
        findDuplicates(new int[]{1, 2, 3, 2, 4, 3}) should return [2, 3] .
        findDuplicates(new int[]{5, 5, 5, 5}) should return [5] .
        Hints:
        Use nested loops to compare each element with every other element.

 */

import java.util.Arrays;
import java.util.Scanner;

public class DuplicateInArray {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        int[] intArrayInput = Utility.convertToIntegerArray(userInput.split(","));
        System.out.println("duplicates are " + Arrays.toString(findDuplicate(intArrayInput)));
    }

    static int[] findDuplicate(int[] array) {
        String duplicateElement = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    if (!duplicateElement.contains(String.valueOf(array[i])) || duplicateElement.equals("")) {
                        duplicateElement += String.valueOf(array[i]).trim();
                    }

                }
            }
        }
        return Utility.convertToIntegerArray(duplicateElement.trim().split(""));
    }
}