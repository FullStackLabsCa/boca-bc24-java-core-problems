package io.reactivestax.problems.stringproblems;

import java.util.Arrays;

public class MergeArray {
    int[] array1 = {1,2,3,4};
    int[] array2 = {5,6,7,8};
    int[] array3 = new int[array1.length + array2.length];

    public void mergeArrays(){

        System.arraycopy(array1, 0,  array3, 0, array1.length);
        System.arraycopy(array2, 0,  array3, array1.length, array2.length);

        System.out.println("Merging two arrays " + Arrays.toString(array3));
    }

    public static void main(String[] args) {
        MergeArray mergeArray = new MergeArray();
        mergeArray.mergeArrays();
    }

}
