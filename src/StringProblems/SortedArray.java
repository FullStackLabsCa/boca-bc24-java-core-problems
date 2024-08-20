package StringProblems;

import java.util.Scanner;

public class SortedArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a value to check if its sorted or not");
        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int[] intInputArray = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            intInputArray[i] = Integer.parseInt(inputs[i].trim());
        }
        System.out.println(isSorted(intInputArray));
    }

    public static boolean isSorted(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
