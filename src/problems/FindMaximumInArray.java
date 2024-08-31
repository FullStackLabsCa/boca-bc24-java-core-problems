package problems;

public class FindMaximumInArray {

    public static void main(String[] args) {
        int[] numbers = {10, 30, 40, 60, 50, 29, 89, -89};
    }

    public static int findMax(int[] numbers) {

        int maxValue= Integer.MIN_VALUE;
        if (numbers == null || numbers.length == 0) {
            maxValue = Integer.MIN_VALUE;
        } else {
            for (int num : numbers) {
                if (maxValue < num) {
                    maxValue = num;
                }
            }

        }
        return maxValue;
    }
}

