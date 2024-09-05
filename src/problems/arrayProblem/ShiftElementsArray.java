package problems.arrayProblem;

import java.util.Arrays;

public class ShiftElementsArray {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int position = 5;
        int[] returnArray = shiftArray(arr, position);
        System.out.println("Return New Array");
        for (int element : returnArray) {
            System.out.print(element);
        }
        System.out.println("\n" +Arrays.toString(returnArray));
    }


    public static int[] shiftArray(int[] arr, int position) {
        int[] newArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {

            if (i + position < arr.length) {
                newArray[i + position] = arr[i];
            } else {
                newArray[i - (arr.length - position)] = arr[i];
            }
        }
        return newArray;
    }

}
