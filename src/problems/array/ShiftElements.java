package problems.array;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class ShiftElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value of array");
        String inputValue = scanner.nextLine().trim();

        String[] splitInputValue = inputValue.split(",");
        int[] intArray = new int[splitInputValue.length];

        for (int i = 0; i < splitInputValue.length; i++) {
            intArray[i] = Integer.parseInt(splitInputValue[i].trim());
        }

        System.out.println("Enter a position of array to shift it");
        int position = scanner.nextInt();

        System.out.println("result = " + Arrays.toString(shiftElementInArray(intArray, position)));
        ;
    }

    public static int[] shiftElementInArray(int[] array, int positions) {
        int[] newArray = new int[array.length];
        int j = 0;
        int indexPosition = array.length - positions;
        for (int i = 0; i < array.length; i++) {
            newArray[j] = array[indexPosition];
            if (j < array.length - 1) {
                j++;
            }
            indexPosition++;
            if (indexPosition > array.length - 1) {
                indexPosition = 0;
            }
        }
        return newArray;
    }
}
