package problems.arrays_problems.min_max_array;

public class MinMaxElementInArray {
    public static int[] findMinMax(int[] array) {
        int max = FindMaximumInArray.findMax(array);
        int min = FindMinimumInArray.findMin(array);

        return new int[]{min,max};
    }
}

