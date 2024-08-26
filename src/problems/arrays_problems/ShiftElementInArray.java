package problems.arrays_problems;

import java.util.Arrays;

public class ShiftElementInArray {
    public static int[] shiftArray(int[] array, int positions) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int temp = i + positions;
            if (temp < array.length) {
                result[i + positions] = array[i];

            } else {
                result[(temp - (array.length))] = array[i];
            }

        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("ShiftElementInArray.shiftArray(new int[]{1,2,3,4}, 2) = " +
                Arrays.toString(ShiftElementInArray.shiftArray(new int[]{1, 2, 3, 4}, 2)));
        System.out.println("ShiftElementInArray.shiftArray(new int[]{10,20,30}, 1) = "
                + Arrays.toString(ShiftElementInArray.shiftArray(new int[]{10, 20, 30}, 1)));
    }
}
