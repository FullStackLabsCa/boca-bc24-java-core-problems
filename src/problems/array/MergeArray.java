package problems.array;

import java.util.Arrays;

public class MergeArray {
    public static void main(String[] args) {
        int[] arr1= {1, 2};
        int[] arr2= {3, 4};

        System.out.println(Arrays.toString((mergeArrays(arr1, arr2))));
    }
    public static int[] mergeArrays(int[] array1, int[] array2){
        int[] array3 = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, array3, 0, array1.length);
        System.arraycopy(array2, 0, array3, array1.length, array2.length);
        return array3;
    }
}
