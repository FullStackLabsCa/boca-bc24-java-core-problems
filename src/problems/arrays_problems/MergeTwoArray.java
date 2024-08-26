package problems.arrays_problems;

import java.util.Arrays;

public class MergeTwoArray {


    public static int[] mergeArrays(int[] array1, int[] array2) {
        int[] mergedArray = new int[((array1.length) + (array2.length))];
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.arraycopy(array2, 0, mergedArray, (array1.length), array2.length);
        return mergedArray;
    }

    public static void main(String[] args) {
        System.out.println("************* Merged Array Output ************");
        System.out.println(Arrays.toString(MergeTwoArray.mergeArrays
                (new int[]{1, 2}, new int[]{3, 4})));

    }
}
