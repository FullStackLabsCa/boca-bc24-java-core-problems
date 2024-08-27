package problems;

import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {
        int [] arr1 = {1,2,3,4};
        int [] arr2 = {4,5,6,7};

        System.out.println(Arrays.toString(mergeArray(arr1, arr2)));;
     }
    public static int[] mergeArray(int[] arr1, int[] arr2){
        int size = arr1.length+arr2.length;
        int[] arr3 = new int[size];

        System.arraycopy(arr1,0,arr3,0,arr1.length);
        System.arraycopy(arr2,0,arr3,arr1.length,arr2.length);

        return arr3;
    }
}
