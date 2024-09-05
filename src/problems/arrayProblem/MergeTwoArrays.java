package problems.arrayProblem;

import java.util.Arrays;

public class MergeTwoArrays {
    public static void main(String[] args) {

        int[] arr1 = {10, 20};
        int[] arr2 = {30, 40};
        int[] mergedArray = mergeTwoArray(arr1, arr2);

        System.out.println("Merged Array");
        for (int element: mergedArray){
            System.out.print(element + " ");
        }
        System.out.println("\nAns as per question");
        System.out.println(Arrays.toString(mergedArray));
    }

    private static int[] mergeTwoArray(int[] arr1, int[] arr2) {

        int[] mergedArray = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
        System.arraycopy(arr2, 0, mergedArray, arr1.length, arr2.length);
        return mergedArray;

    }


}
