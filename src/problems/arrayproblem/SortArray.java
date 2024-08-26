package arrayproblem;

import java.util.Scanner;

public class SortArray {
    static boolean isSorted(int [] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            for (int j = i + 1; j < intArray.length; j++) {
                if (intArray[i] > intArray[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an array length::");
        int size = scanner.nextInt();
        System.out.println("Enter an array element::");
        int [] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Check whether array is sorted or not.!");
        System.out.println("Array is Sorted? " + isSorted(arr));
    }
}
