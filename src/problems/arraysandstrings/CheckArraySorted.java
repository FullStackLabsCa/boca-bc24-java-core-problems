package problems.arraysandstrings;

import java.util.Scanner;

public class CheckArraySorted {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of array: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];

        System.out.println("Enter " + size + " numbers: ");

        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Array is " + (isSorted(arr) ? "sorted" : "not sorted"));
    }

    public static boolean isSorted(int[] array) {
        boolean sorted = true;

        int first = array[0];
        for (int num : array) {
            if (first > num) {
                sorted = false;
                break;
            }
            first = num;
        }

        return sorted;
    }
}
