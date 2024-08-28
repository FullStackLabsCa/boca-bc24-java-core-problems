package arrayproblem;

import java.util.Arrays;

public class MergeArray {
    public static void main(String[] args) {
        int[] firstInputArray = {5, 4, 3, 1,75};
        int[] secondInputArray = {47, 32, 9, 3, 78, 8, 23};
        int[] finalArray = mergeArrays(firstInputArray, secondInputArray);
        int[] sortedArray = new int[firstInputArray.length + secondInputArray.length];
        System.arraycopy(finalArray, 0, sortedArray, 0, finalArray.length);
        Arrays.sort(sortedArray);
        System.out.println("Merged array is : ");

        for (int i : finalArray) {
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.println("Merged array : ");

        for (int i : sortedArray) {
            System.out.print(i + ", ");
        }
    }


    static int[] mergeArrays(int[] firstArray, int[] secondArray) {

        int[] finalArray = new int[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, finalArray, 0, firstArray.length);
        System.arraycopy(secondArray, 0, finalArray, firstArray.length, secondArray.length);
        return finalArray;
    }
}