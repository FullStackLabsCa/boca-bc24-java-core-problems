package basic;

import java.util.Scanner;

public class Problem15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a array length: ");
        int araryLength = scanner.nextInt();
        int [] arr = new int[araryLength];
        int sumOfOddNumbers = 0, sumOfEvenNumbers = 0;
        System.out.println("Enter elements of array: ");
        for (int i = 0; i < araryLength; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 0; i < araryLength; i++) {
            if (arr[i] % 2 == 0) {
                sumOfEvenNumbers += arr[i];
            } else {
                sumOfOddNumbers += arr[i];
            }
        }
        System.out.println("Sum of the odd array element is: " + sumOfOddNumbers);
        System.out.println("Sum of the even array element is: " + sumOfEvenNumbers);
    }
}
