package collections;

import java.util.*;

public class FindduplicateWithSet {
    public static void main(String[] args) {
        Integer[] dupNums =    findDuplicateElements(new int[]{1, 2, 3, 2, 4, 3, 4});
        for(int num: dupNums){
            System.out.println(num+" ");
        }
    }

    private static Integer[] findDuplicateElements(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicate = new HashSet<>();
        for (int num: nums){
            if(!seen.add(num)){
                duplicate.add(num);
            }
        }
        Integer[] intArray= duplicate.toArray(new Integer[duplicate.size()]);
        return intArray;
    }
}
