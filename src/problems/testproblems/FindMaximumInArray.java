package testproblems;

import java.util.Scanner;

public class FindMaximumInArray {
    public static int findMax(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        for (int j : numbers) {
            if (j > max) {
                max = j;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
        System.out.println("Please enter the size of the array: ");
        size = scanner.nextInt();
        int [] myArray = new int[size];

        System.out.println("Please enter element of the array: ");
        for (int i = 0; i < size; i++) {
            myArray[i] = scanner.nextInt();
        }

        int max = FindMaximumInArray.findMax(myArray);
        System.out.println(max);
    }
}
