package problems;

import java.util.Arrays;

public class ShiftElementsInArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shiftArray(new int[]{1, 2, 3, 4}, 2)));

    }
    public static int[] shiftArray(int[] array, int positions){
        int[] newArr = new int[array.length];
        for(int i = 0;i< array.length;i++){
            {
                newArr[(i+positions) % array.length] = array[i];

            }
        }
        return newArr;
    }
}
