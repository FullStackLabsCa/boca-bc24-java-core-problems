package problems;

import java.util.Arrays;

public class FindMaximumInArray {
    public static int findMax(int[] numbers) {
        int maxNum;

        if (numbers == null) {
            maxNum = Integer.MIN_VALUE;
        } else if (numbers.length == 0){
            maxNum = Integer.MIN_VALUE;
        } else {
            maxNum = Arrays.stream(numbers).max().getAsInt();
        }

        return maxNum;
    }
}