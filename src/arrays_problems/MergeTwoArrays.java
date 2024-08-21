package arrays_problems;

import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoArrays {
// We can also do with the copy String method
    public static int[] mergeArrays(int[] firstArray, int[] secondArray) {
        int mergeArraySize = firstArray.length + secondArray.length;
        int[] mergedArray = new int[mergeArraySize];
//        System.arraycopy(firstArray,0,mergedArray,0,firstArray.length);
//        System.arraycopy(secondArray,0,mergedArray,firstArray.length,secondArray.length);
        for (int i = 0; i < firstArray.length; i++) {
            mergedArray[i]=firstArray[i];
        }
        int k=0;
        for (int j = firstArray.length; j < mergedArray.length ; j++) {
            mergedArray[j]=secondArray[k];
            k++;
        }
        return mergedArray;
    }
    public static void main(String[] args) {
        System.out.println("Please provide the size of first array to be Merge");
        Scanner scanner = new Scanner(System.in);
        int firstArraySize = scanner.nextInt();
        int[] firstArray = new int[firstArraySize];
        System.out.println("Please enter the elements of first array");
        for (int firstArrayIndex = 0; firstArrayIndex < firstArraySize; firstArrayIndex++) {
            firstArray[firstArrayIndex] = scanner.nextInt();
        }
        System.out.println("Please provide the size of first array to be Merge");
        int secondArraySize = scanner.nextInt();
        int[] secondArray = new int[secondArraySize];
        System.out.println("Please enter the elements of first array");
        for (int secondArrayIndex = 0; secondArrayIndex < firstArraySize; secondArrayIndex++) {
            secondArray[secondArrayIndex] = scanner.nextInt();
        }
        System.out.println("Merged Array is : "+ Arrays.toString(MergeTwoArrays.mergeArrays(firstArray, secondArray)));
    }
}
