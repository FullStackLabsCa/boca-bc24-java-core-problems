package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDuplicate {
    public static void main(String[] args) {
        int[] array = {1,1,2,3,4,2,5};
        findDuplicate(array);
    }

    public static void findDuplicate(int[] array){
        List<Integer> output = new ArrayList<>();
        int[] tempArray = Arrays.copyOf(array,array.length);

        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < tempArray.length-1; j++){
                if(array[i] == tempArray[j+1]){
                   output.add(array[j]);
                }
            }
        }
        System.out.println(output);
    }
}
