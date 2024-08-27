package problems.old_assignments.problems;

public class SumOddNumber {
    public static void main(String[] args) {
        int n = 50;
        int oddSum = 0;

        for(int i = 1; i <= 2 * n; ++i) {
            if ((i & 1) == 1) {
            oddSum += i;
            }

        }
        System.out.println("Sum of First " + n + " Odd numbers = " + oddSum);
    }
}