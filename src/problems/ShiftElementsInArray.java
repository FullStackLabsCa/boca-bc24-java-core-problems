package problems;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftElementsInArray {
    public static void main(String[] args) {
        System.out.println( Arrays.toString(shiftArray(new int [] {1,2,3,4,7,3,2,6}, 3 )));
    }
    public static int[] shiftArray(int[] array, int positions){
       int[] newarr = new int[array.length];
       for(int i=0;i<array.length;i++){
           newarr[(i+positions) % array.length] = array[i];
       }
       return newarr;
    }
}
