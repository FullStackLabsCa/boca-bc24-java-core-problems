package problems.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("java:S106")
public class FindFirstDuplicateInArray {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5, 2, 4, 3};
        Arrays.sort(intArray);
        int firstDuplicate = 0;

        for (int i = 1; i < intArray.length; i++) {
            if (intArray[i] == intArray[i - 1]) {
                firstDuplicate = intArray[i];
                break;
            }
        }

        System.out.println("firstDuplicate = " + firstDuplicate);
    }
}
