package problems;

public class FindMaximumInArray {

    public static int findMax ( int[] numbers){
            if (numbers == null || numbers.length == 0) {
                return Integer.MIN_VALUE;
            }

            int max = numbers[0];
            for (int number : numbers) {
                if (number > max) {
                    max = number;
                }
            }
            return max;
        }



}
