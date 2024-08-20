package problems;

import java.util.Scanner;

public class FindMaximumInArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the numbers to find the maximum Value");
        String[] stringNumber = scanner.nextLine().split(",");
        int[] numbers = new int[stringNumber.length];
        for (int i = 0; i < stringNumber.length; i++) {
            numbers[i] = Integer.parseInt(stringNumber[i].trim());
        }
        findMax(numbers);
    }

    public static int findMax(int[] numbers) {
        try {
            if (numbers.length == 0) {
                System.out.println("Please provide number to find maximum");
                return Integer.MIN_VALUE;
            }
            int max = numbers[0];
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }
            }
            System.out.println("MAx= " + max);
            return max;
        } catch (NullPointerException e) {
            return Integer.MIN_VALUE;
        }
    }
}
