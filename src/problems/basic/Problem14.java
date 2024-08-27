package basic;

import java.util.Scanner;

public class Problem14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a array length: ");
        int araryLength = scanner.nextInt();
        int [] arr = new int[araryLength];
        int sum = 0;
        System.out.println("Enter elements of array: ");
        for (int i = 0; i < araryLength; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 0; i < araryLength; i++) {
           sum += arr[i];
        }
        System.out.println("Sum of the array element is: " + sum);
    }
}
