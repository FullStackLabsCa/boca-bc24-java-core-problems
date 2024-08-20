package problems.arrays;

import java.util.Arrays;

public class DuplicateElement {
    public static int[] findDuplicates(int[] array) {
        int[] result = new int[array.length];
        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    for (int k = 0; k < result.length; k++) {
                        result[k] = array[i];
                    }

                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Arrays.toString(DuplicateElement.findDuplicates(new int[]{1, 2, 3, 2, 4, 3})) = "
                + Arrays.toString(DuplicateElement.
                findDuplicates(new int[]{1,2,3,2,4,3})));
    }
}
