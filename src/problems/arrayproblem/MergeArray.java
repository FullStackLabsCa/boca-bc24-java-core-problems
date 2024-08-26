package arrayproblem;

import java.util.Arrays;
import java.util.Scanner;

public class MergeArray {
    static int[] mergeArrays(int [] intArray1, int [] intArray2) {
        int mergedArray [] = new int[intArray1.length + intArray2.length];
        System.arraycopy(intArray1, 0,  mergedArray, 0, intArray1.length);
        System.arraycopy(intArray2, 0,  mergedArray, intArray1.length, intArray2.length);
        return mergedArray;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first array length::");
        int arySize1 = scanner.nextInt();

        System.out.println("Enter first array element::");
        int [] arr1 = new int[arySize1];
        for (int i = 0; i < arySize1; i++) {
            arr1[i] = scanner.nextInt();
        }

        System.out.println("Enter second array length::");
        int arySize2 = scanner.nextInt();
        int [] arr2 = new int[arySize2];
        for (int i = 0; i < arySize2; i++) {
            arr2[i] = scanner.nextInt();
        }

        System.out.println("Merged array= " + Arrays.toString(mergeArrays(arr1, arr2)));
    }
}
