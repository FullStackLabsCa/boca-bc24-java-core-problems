package problems;

import java.util.Arrays;

public class FindSmallestAndLargestElementInAnArray {
    public static void main(String[] args) {
        int[] input = {4, 2, 5, 3, -4, 0, 234};
        int[] result = findMinMax(input);
        System.out.println("Smallest element in the input array  :" + result[0]);
        System.out.println("Largest element in the input array   :" + result[1]);
    }

    static int[] findMinMax(int[] input) {
        int[] result = new int[2];
        Arrays.sort(input);
        System.arraycopy(input, 0, result, 0, 1);
        System.arraycopy(input, input.length - 1, result, 1, 1);
        return result;
    }
}
