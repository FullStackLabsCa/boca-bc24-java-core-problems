package stringsAndArraysProblems;

import java.util.ArrayList;
import java.util.List;

public class DuplicateNumInArray {

    public static int[] findDuplicateNum(int[] integerArray) {

        List<Integer> duplicatesList = new ArrayList<>();

        for (int i = 0; i < integerArray.length; i++) {
            // Check if current element is zero or already in duplicatesList
            if (integerArray[i] == 0 || duplicatesList.contains(integerArray[i])) {
                continue;
            }

            // Check for duplicates
            for (int j = i + 1; j < integerArray.length; j++) {
                if (integerArray[i] == integerArray[j]) {
                    duplicatesList.add(integerArray[i]);
                    break;
                }
            }
        }

        // Convert List to Array
        int[] arrayOfDupes = new int[duplicatesList.size()];
        for (int i = 0; i < duplicatesList.size(); i++) {
            arrayOfDupes[i] = duplicatesList.get(i);
        }

        return arrayOfDupes;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 4, 5, 1, 6, 0, 2};
        int[] duplicates = findDuplicateNum(array);

        System.out.print("Duplicates from the given array: ");
        for (int num : duplicates) {
            System.out.print(num + " ");
        }
    }
}