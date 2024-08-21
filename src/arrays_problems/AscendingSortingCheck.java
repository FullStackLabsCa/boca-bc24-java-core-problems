package arrays_problems;

import java.util.Scanner;

public class AscendingSortingCheck {
    public static boolean isSorted(int[] input) {
        boolean sorted = true;
        for (int i = 0; i <input.length-1;i++) {
            if (input[i]>input[i+1]){
                sorted =false;
                break;
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        System.out.println("Please provide the size of an array to be sorted");
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
        System.out.println("\nIs Array Sorted : "+AscendingSortingCheck.isSorted(inputArray));
    }
}
