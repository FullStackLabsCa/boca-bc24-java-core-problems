package collections;

import java.util.*;

public class Findduplicate {
    public static void main(String[] args) {
        Integer[] dupNums =    findDuplicateElements(new int[]{1, 2, 3, 2, 4, 3});
        for(int num: dupNums){
            System.out.println(num+" ");
        }
    }

    private static Integer[] findDuplicateElements(int[] nums) {
        HashMap<Integer, Integer> elements = new HashMap<>();
        List<Integer> duplicates = new ArrayList<Integer>();
        for(int num : nums){
            if(elements.containsKey(num)){
                elements.put(num, elements.get(num)+1);
            }else{
                elements.put(num, 1);
            }
        }
        for(Map.Entry<Integer, Integer> hs: elements.entrySet()){
            if(hs.getValue()> 1){
                duplicates.add(hs.getKey());
            }
        }
        Integer[] arr = new Integer[duplicates.size()];
        arr = duplicates.toArray(arr);
        return arr;
    }
}
