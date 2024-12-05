package problems.codeproblems;

public class ArrayNumberSum {
    public static void main(String[] args) {
        int[] numberArray = {2, 3, 8, 9, 1, 6};
        int sumNumberArray = 0;
        for (int i : numberArray) {
            sumNumberArray += i;
        }
        System.out.println("Sum of numbers in the array is: " + sumNumberArray);
    }
}
