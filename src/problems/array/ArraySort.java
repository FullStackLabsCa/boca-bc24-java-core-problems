package problems.array;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class ArraySort {
    public static void main(String[] args) {
        boolean isValueNull = false;
        System.out.println("Please enter a values of array ");
        Scanner value = new Scanner(System.in);
        String inputValue = (value.nextLine()).trim();

        String[] splitInputValue = inputValue.split(",");

        int[] numbers = new int[splitInputValue.length];

        for (int i = 0; i < splitInputValue.length; i++) {
            numbers[i] = Integer.parseInt(splitInputValue[i].trim());
        }
        boolean result;
        if (inputValue.isEmpty()) {
            isValueNull = true;
            System.out.println("Please enter a valid value");
        }
        result = isSorted(numbers);

        if (!isValueNull)
            System.out.println("Given Array sorted: " + result);
    }

    public static boolean isSorted(int[] array) {
        boolean isSorted = true;
        int firstElement = array[0];

        for (int num : array) {
            if (firstElement > num)
                isSorted = false;
        }
        return isSorted;
    }
}
