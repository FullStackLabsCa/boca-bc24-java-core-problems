package problems;

import java.text.spi.NumberFormatProvider;
import java.util.Scanner;

public class FindMaximumInArray {
    public static void main(String[] args) {

    }
    public static int findMax(int[] arr) {
//
        if(arr==null || arr.length == 0){
            return Integer.MIN_VALUE ;
        }

            //[2,4,5,1,6]

            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
           return max;


    }
}
