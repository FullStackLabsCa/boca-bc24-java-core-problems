package problems.problems;

public class SumOddOrEven {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        int evenSum = 0;
        int oddSum = 0;

        for (int i : array) {
            if (i % 2 == 0) {
                evenSum += i;
            } else {
                oddSum += i;
            }
        }

        System.out.println("Odd Sum: " + oddSum);
        System.out.println("Even Sum: " + evenSum);
    }

}

