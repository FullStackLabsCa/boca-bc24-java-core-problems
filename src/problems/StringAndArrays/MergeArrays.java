package problems.StringAndArrays;

import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {

//        int[] firstArray = {1,2,2,3,4};
        int[] firstArray = {1, 2, 3, 2, 2, 4, 3, 1, 4};
        int[] secondArray = {50, 60, 7};

        System.out.println("Merged Arrays : " + Arrays.toString(mergeArrays(firstArray, secondArray)));


    }

    private static int[] mergeArrays(int[] firstArray, int[] secondArray) {
        int[] outputArray = new int[firstArray.length + secondArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            outputArray[i] = firstArray[i];
        }
        for (int i = 0; i < secondArray.length; i++) {
            outputArray[firstArray.length + i] = secondArray[i];
        }
//        System.out.println(Arrays.toString(outputArray));
        Arrays.sort(outputArray);
        return outputArray;
    }
}
