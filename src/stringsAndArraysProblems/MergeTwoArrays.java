package stringsAndArraysProblems;

public class MergeTwoArrays {

    public static int[] mergeArray(int[] arrayOne, int[] arrayTwo) {
        int[] mergedArray = new int[arrayOne.length + arrayTwo.length];
        int k = 0;
        for (int i = 0; i < arrayOne.length; i++) {
            mergedArray[i] = arrayOne[i];
            k = i;
        }
        for (int j = 0; j < arrayTwo.length; j++) {
            k++;
            mergedArray[k] = arrayTwo[j];
        }

        return mergedArray;
    }
}
