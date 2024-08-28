package problems;

import java.util.Scanner;

public class SumOddNumOfArray {
    public static void main(String[] args) {
        System.out.println("Please enter the size of array");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] ints = new int[size];
        System.out.println("Please enter the elements");
        int duplicate = 0;
        int i, sum = 0;
        for (i = 0; i < size; i++) {
            ints[i] = scanner.nextInt();
        }
        for (int a : ints) {
            if (a % 2 == 0) {
                sum = sum + a;
            }
        }
        System.out.println("Sum of Even elements of array is " + sum);
    }
}