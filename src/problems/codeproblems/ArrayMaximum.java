package problems.codeproblems;

public class ArrayMaximum {
    public static void main(String[] args) {
        int[] numbers = {-5, 7, 8, 1, 2, 9};
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        System.out.println("Maximum number in array is: " + max);
    }
}
