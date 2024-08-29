package problems.arraysandstrings;

import java.util.Scanner;

public class MergeTwoArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size of first array: ");
        int n1 = scanner.nextInt();

        System.out.println("Enter elements of first array: ");
        int[] array1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            array1[i] = scanner.nextInt();
        }

        System.out.println("Enter size of second array: ");
        int n2 = scanner.nextInt();

        System.out.println("Enter elements of second array: ");
        int[] array2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            array2[i] = scanner.nextInt();
        }

        System.out.println("Merged array is: ");
        int[] mergedArray = mergeArrays(array1, array2);
        for (int num : mergedArray) {
            System.out.println(num);
        }
    }

    public static int[] mergeArrays(int[] array1, int[] array2) {
        int[] mergedArray = new int[array1.length + array2.length];

        System.arraycopy(array1, 0, mergedArray, 0, array1.length);
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);

        return mergedArray;
    }
}
