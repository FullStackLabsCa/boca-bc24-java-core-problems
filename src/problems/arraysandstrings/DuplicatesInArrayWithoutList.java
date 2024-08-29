package problems.arraysandstrings;

import java.util.Arrays;
import java.util.Scanner;

public class DuplicatesInArrayWithoutList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter the elements of array: ");

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        int[] duplicates = findDuplicates(array);

        StringBuilder str = new StringBuilder("[");

        for (int num : duplicates) {
            str.append(num).append(" ");
        }
        str = new StringBuilder(str.toString().trim().replace(" ", ","));

        System.out.println(str.append("]"));
    }

    public static int[] findDuplicates(int[] array) {
        Arrays.sort(array);
        int[] duplicates = new int[array.length];
        Arrays.fill(duplicates, Integer.MIN_VALUE);
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1 && array[i] == array[i + 1] && !existInArray(array[i], duplicates)) {
                duplicates[count] = array[i];
                count++;
            }
        }

        int[] duplicatesArray = new int[count];

        System.arraycopy(duplicates, 0, duplicatesArray, 0, count);

        return duplicatesArray;
    }

    public static boolean existInArray(int num, int[] array) {
        for (int j : array) {
            if (j == num) {
                return true;
            }
        }

        return false;
    }
}