package problems.StringAndArrays;

import java.util.Arrays;

public class ShiftArray {
    public static void main(String[] args) {

        int[] firstArray = {1, 2, 3,2, 2, 4, 3,1,4};
        System.out.println("SHIFT array : "+ Arrays.toString(shiftArray(firstArray, 4)));

    }

    public static int[] shiftArray(int[] array, int position) {
        int[] returnArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {

            if (i + position < array.length) {
                returnArray[i + position] = array[i];
            } else {
                returnArray[i - (array.length - position)] = array[i];
            }
        }
        return returnArray;
    }
}
