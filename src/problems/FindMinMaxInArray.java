package problems;

import java.util.Arrays;

public class FindMinMaxInArray {
    public static void main(String[] args) {
        int[] ar = {8,2,4,7,3,6,5};
        System.out.println("The min and max of array is as follow: ");
        System.out.println(Arrays.toString(findMinMax(ar)));

    }
    public static int[] findMinMax(int[] array){
        int[] newarray = new int[2];
        int min = array[0];
        int max = 0;
        for(int i = 0;i<array.length;i++){
            if(min > array[i]){
                min = array[i];
            }
            if(max < array[i]){
                max = array[i];
            }
        }
        newarray[0] = min;
        newarray[1] = max;
        return newarray;

    }
}
