package problems;

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

    }
}
