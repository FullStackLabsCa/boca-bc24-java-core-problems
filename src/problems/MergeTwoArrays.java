package problems;

import java.util.Arrays;

public class MergeTwoArrays {
    public static void main(String[] args) {
        int[] firstInputArray = {2, 4, 5, 2};
        int[] secondInputArray = {238, 32, 1, 4, 23, 3, 43};
        int[] resultArray = mergeArrays(firstInputArray, secondInputArray);
        int[] sortedArray = new int[firstInputArray.length + secondInputArray.length];
        System.arraycopy(resultArray, 0, sortedArray, 0, resultArray.length);
        Arrays.sort(sortedArray);
        System.out.println("Merged array is : ");

        for (int i : resultArray) {
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.println("Merged array after sort : ");

        for (int i : sortedArray) {
            System.out.print(i + ", ");
        }


    }

    static int[] mergeArrays(int[] firstInputArray, int[] secondInputArray) {
        int[] resultArray = new int[firstInputArray.length + secondInputArray.length];
        System.arraycopy(firstInputArray, 0, resultArray, 0, firstInputArray.length);
        System.arraycopy(secondInputArray, 0, resultArray, firstInputArray.length, secondInputArray.length);
        return resultArray;
    }
}
