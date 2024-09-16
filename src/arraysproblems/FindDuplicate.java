package arraysproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDuplicate {
    public static void main(String[] args) {
        int[] array = {1,1,2,3,4,2,5};
        findDuplicate(array,1);
    }

    public static void findDuplicate(int[] array, int number){
         int count = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] == number) {
                count++;
            }
        }
        System.out.println(count);
    }

}
