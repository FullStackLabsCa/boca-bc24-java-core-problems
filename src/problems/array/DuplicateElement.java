package problems.array;

import java.util.ArrayList;

public class DuplicateElement {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3};

        System.out.println(ArrayList.class.toString());

    }

    public static ArrayList<Integer> findDuplicates(int[] array) {
//        int[] duplicateElements= {};
        ArrayList<Integer> duplicateElements = new ArrayList<Integer>();
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    for (int k = 0; k < duplicateElements.size(); k++) {
                        if (array[i] != duplicateElements.get(k)) {
                            count++;
                            duplicateElements.set(count, array[i]);
                        }
                    }
                }
            }
        }
        return duplicateElements;
    }
}
