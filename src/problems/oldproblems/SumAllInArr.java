package problems.oldproblems;

import java.util.Scanner;

public class SumAllInArr {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the count of number you want to input: ");
        int size = s.nextInt();
        System.out.println("Please enter the numbers: ");
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = s.nextInt();
        }

        long sum = 0;
        for (int num : arr) {
            sum += num;
        }

        System.out.println("Sum of all numbers in array is: " + sum);
    }
}
