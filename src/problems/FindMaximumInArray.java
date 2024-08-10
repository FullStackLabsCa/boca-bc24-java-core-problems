package problems;

import java.util.Arrays;


public class FindMaximumInArray {

    public static void main(String[] args) {
        int[] numbers = new int[0];
        System.out.println(findMax(numbers));
    }
    public static int findMax(int[] numbers) {
        if (numbers==null){
            return Integer.MIN_VALUE;
        }
        int maxValue;
        maxValue = Integer.MIN_VALUE;
        for (int n : numbers) {
            if (maxValue < n) {
                maxValue = n;
            }
        }
        return maxValue;
    }
}
