package problems.array;

import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoArrays {
    public static void main(String[] args) {
        boolean isValueNull = false;
        Scanner input = new Scanner(System.in);

        //first Array
        System.out.println("Please enter a values for first array...");
        String firstArrayValue = input.nextLine();
        if (firstArrayValue == "") {
            isValueNull = true;
            System.out.println("Please enter a valid values for first array...");
        }

        int[] firstArray = splitAndConvertToNumbersArray(firstArrayValue);

        System.out.println(Arrays.toString(firstArray));

        //second Array
        System.out.println("Please enter a values for second array...");
        String secondArrayValue = input.nextLine();
        if (secondArrayValue == "") {
            isValueNull = true;
            System.out.println("Please enter a valid values for second array...");
        }

        int[] secondArray = splitAndConvertToNumbersArray(secondArrayValue);

        System.out.println(Arrays.toString(secondArray));

//        String longestWord = mergeArrays(value);
        if (!isValueNull) System.out.println("Capitalize Words: ");
    }

    public static int[] splitAndConvertToNumbersArray(String str) {
        String[] splitInputValue = str.split(",");
        int[] numbers = new int[splitInputValue.length];

        for (int i = 0; i < splitInputValue.length; i++) {
            numbers[i] = Integer.parseInt(splitInputValue[i].trim());
        }
        return numbers;
    }

    public static int[] mergeArrays(int[] array1, int[] array2) {
        int[] mergedArray = new int[5];
        return mergedArray;
    }
}
