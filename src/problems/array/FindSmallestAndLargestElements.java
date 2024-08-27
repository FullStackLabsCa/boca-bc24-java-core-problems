package problems.array;

import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class FindSmallestAndLargestElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value of array");
        String inputValue = scanner.nextLine().trim();

        String[] splitInputValue = inputValue.split(",");
        int[] intArray = new int[splitInputValue.length];

        for (int i = 0; i < splitInputValue.length; i++) {
            intArray[i] = Integer.parseInt(splitInputValue[i].trim());
        }
        Arrays.sort(intArray);
        int smallElement = intArray[0];
        int largestElement = intArray[intArray.length - 1];

        System.out.println("smallElement = " + smallElement);
        System.out.println("largestElement = " + largestElement);
    }
}
