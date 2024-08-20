package problems.array;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class MergeTwoArrays {
    public static void main(String[] args) {
        boolean isValueNull = false;
        Scanner input = new Scanner(System.in);

        //first Array
        System.out.println("Please enter a values for first array...");
        String firstArrayValue = input.nextLine();
        //null check
        if (firstArrayValue == "") {
            isValueNull = true;
            System.out.println("Please enter a valid values for first array...");
        }

        int[] firstArray = splitAndConvertToNumbersArray(firstArrayValue);
//        System.out.println("firstArray: "+ Arrays.toString(firstArray));

        //second Array
        System.out.println("Please enter a values for second array...");
        String secondArrayValue = input.nextLine();
        if (secondArrayValue == "") {
            isValueNull = true;
            System.out.println("Please enter a valid values for second array...");
        }

        int[] secondArray = splitAndConvertToNumbersArray(secondArrayValue);
//        System.out.println("secondArray: "+ Arrays.toString(secondArray));

        int[] mergedArray = mergeArrays(firstArray, secondArray);

//        String longestWord = mergeArrays(value);
        if (!isValueNull) System.out.println("Merged Array: " + Arrays.toString(mergedArray));
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
        int arrayLength = array1.length + array2.length;
        int[] mergedArray = new int[arrayLength];

        for (int i = 0; i < array1.length; i++) {
            mergedArray[i] = array1[i];
        }

        for (int j = array1.length; j < mergedArray.length; j++) {
            mergedArray[j] = array2[j-array1.length];
        }

        return mergedArray;
    }
}
