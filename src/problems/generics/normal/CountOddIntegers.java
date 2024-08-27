package problems.generics.normal;

import java.util.Scanner;

public class CountOddIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements you would like to add: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];

        System.out.println("Enter elements one by one: ");
        for (int i = 0; i < size; i++) {
            int element = scanner.nextInt();
            arr[i] = element;
        }

        System.out.println("Number of Odd elements in the given collection is: " + countOddIntegers(arr));
    }

        public static <T> int countOddIntegers(int[] array) {
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] % 2 != 0) {
                    count++;
                }
            }
            return count;
    }
}