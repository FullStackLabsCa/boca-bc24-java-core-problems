package problems.core;

@SuppressWarnings("java:S106")

public class FindMaximumInArray {
    public static int findMax(int[] numbers) {
        if (numbers == null)
            return Integer.MIN_VALUE;

        int maxValue = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > maxValue) maxValue = num;
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] integers = {12,33,21,323,78};
        int max = findMax(integers);
        System.out.println("max = " + max);
    }
}
