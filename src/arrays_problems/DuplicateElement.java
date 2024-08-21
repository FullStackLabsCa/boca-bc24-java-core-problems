package arrays_problems;

import java.util.Arrays;
import java.util.Scanner;

public class DuplicateElement {

    public static boolean foundInArray(int[] array, int element) {
        boolean b = false;
        for (int m = 0; m < array.length; m++) {
            if (array[m] == element) {
                b = true;
                break;
            } else {
                b = false;
                break;
            }
        }
        return b;
    }

    public static int[] findDuplicates(int[] input) {
        int count = 0;
        int l = 0;
        int[] temp = new int[input.length];
        FIRST:
        for (int i = 0; i < input.length; i++) {
            SECOND:
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j] && !foundInArray(temp, input[i])) {
                    count++;
                    for (; l < count; l++) {
                        temp[l] = input[i];
                    }
                    break;
                }
            }
        }
        int[] duplicates = new int[count];
        System.arraycopy(temp, 0, duplicates, 0, duplicates.length);
        return duplicates;
    }

    public static void main(String[] args) {
        System.out.println("Please provide the size of an array to be checked");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] inputArray = new int[size];
        System.out.println("Please enter the elements of an array");
        for (int userArrayIndex = 0; userArrayIndex < size; userArrayIndex++) {
            inputArray[userArrayIndex] = scanner.nextInt();
        }
        System.out.println("User enter array");
        for (int userArray : inputArray) {
            System.out.print(userArray + " ");
        }
        System.out.println("\nDuplicate Elements in array : " + Arrays.toString(DuplicateElement.findDuplicates(inputArray)));
    }
}
