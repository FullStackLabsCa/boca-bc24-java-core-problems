package problems.array;

import java.util.*;

@SuppressWarnings("java:S106")
public class FindDuplicate {
    public static void main(String[] args) {
        int[] intArray = {1,2,3,4,5,2,4,3};
        Set<Integer> duplicateIntArray = new HashSet<>();
        Arrays.sort(intArray);

        for (int i = 1; i <intArray.length ; i++) {
            if(intArray[i] == intArray[i-1]){
                duplicateIntArray.add(intArray[i]);
            }
        }

        System.out.println("duplicateIntArray = " + duplicateIntArray);
    }
}
