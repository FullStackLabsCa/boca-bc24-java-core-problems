package problems.arrays_problems.min_max_array;

import java.util.Arrays;

public class MinMaxLauncher {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(MinMaxElementInArray.findMinMax(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(MinMaxElementInArray.findMinMax(new int[]{10, -3, 7, 2})));
    }
}
