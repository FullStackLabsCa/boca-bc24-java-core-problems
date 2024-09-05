package problems.arrayProblem.duplicateElementInArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DuplicateElementInArray {

    public static void main(String[] args) {

        int[] array1 = {1, 2, 3, 2, 4, 3};
        int[] array2 = {5, 5, 5, 5};

        int[] duplicates1 = findDuplicates(array1);
        int[] duplicates2 = findDuplicates(array2);

        System.out.println(Arrays.toString(duplicates1));
        System.out.println(Arrays.toString(duplicates2));

    }

    private static int[] findDuplicates(int[] array) {

        List<Integer> duplicate = new ArrayList<>();

        for (int i=0; i<array.length; i++){
            for( int j=i+1; j<array.length; j++){
                if (array[i] == array[j] && !duplicate.contains(array[i])){
                    duplicate.add(array[i]);
                }
            }
        }

        // convert list to array
        int[] result = new int[duplicate.size()];
        for (int i=0; i<result.length; i++){
            result[i] = duplicate.get(i);
        }
        return result;
    }


}
