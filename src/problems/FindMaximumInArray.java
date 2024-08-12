package problems;

import java.util.Scanner;

public class FindMaximumInArray {
    public static int findMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }


    public static void main(String[] args) {
       int[] array = {122,122,1342,1,2,3,1};
        System.out.println(findMax(array));
    }

}


