package problems;

import java.util.Arrays;

public class FindDuplicateInAnArray {
    public static void main(String[] args) {
        int[] input = {3, 2, 3, 2, 4, 5, 3, 7, 8, 5, 43, 4, 2, 0, 0};
        System.out.println("Input array : ");
        for (int value : input) {
            System.out.print(value + " ");
        }
        int[] result = findDuplicates(input);
        System.out.println("\n\nDuplicate elements in the array : ");
        for (int value : result) {
            System.out.print(value + " ");
        }
    }

    static int[] findDuplicates(int[] input) {
        Arrays.sort(input);
        int[] tempArray = new int[input.length];
        boolean existingDuplicate;
        int noOfDuplicates = 0;
        for (int i = 0; i < input.length - 1; i++) {
            existingDuplicate = false;
            if (input[i] == input[i + 1]) {
                if (noOfDuplicates == 0) {
                    tempArray[0] = input[i];
                    noOfDuplicates++;
                } else {
                    for (int value : tempArray) {
                        if (value == input[i]) {
                            existingDuplicate = true;
                            break;
                        }
                    }
                    if (!existingDuplicate) {
                        tempArray[noOfDuplicates] = input[i];
                        noOfDuplicates++;
                    }

                }

            }
        }
        int[] result = new int[noOfDuplicates];
        System.arraycopy(tempArray, 0, result, 0, noOfDuplicates);
        return result;
    }
}
