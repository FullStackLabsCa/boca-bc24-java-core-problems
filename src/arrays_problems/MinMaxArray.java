package arrays_problems;

import java.util.Arrays;
import java.util.Scanner;

public class MinMaxArray {
    public static int[] findMinMax(int[] array) {
        int max = array[0], min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (min>array[i]) {
                min = array[i];
            } else if (max<array[i]) {
                max=array[i];
            }
        }
        int[] minMAaxArray = new int[2];
        minMAaxArray[0] = min;
        minMAaxArray[1] = max;
        return minMAaxArray;
    }

    public static void main(String[] args) {
        System.out.println("Please provide the size of an array to be sorted");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] inputArray = new int[size];
        System.out.println("Please enter the elements of an array");
        for (int userArrayIndex = 0; userArrayIndex < size; userArrayIndex++) {
            inputArray[userArrayIndex] = scanner.nextInt();
        }
        System.out.println("User enter array");
        for (int userArray : inputArray) {
            System.out.print(userArray + " ");
        }
        System.out.println("\nIs Array Sorted : " + Arrays.toString(MinMaxArray.findMinMax(inputArray)));
    }
}
