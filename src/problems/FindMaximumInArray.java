package problems;

public class FindMaximumInArray {
    public static Object findMax(int[] numbers) {

        if (numbers != null && numbers.length > 0) {
            int max_val = numbers[0];
            for (int val : numbers) {
                if (max_val < val) {
                    max_val = val;
                }
            }
            return max_val;
        }else{
            return Integer.MIN_VALUE;
        }
    }
}
