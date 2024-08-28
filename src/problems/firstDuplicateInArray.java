package problems;

import java.util.Scanner;

public class firstDuplicateInArray {
    public static void main(String[] args) {
        System.out.println("Please enter the size of array");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] ints = new int[size];
        System.out.println("Please enter the elements");
        int duplicate = 0;
        int i, k = 0;
        for (i = 0; i < size; i++) {
            ints[i] = scanner.nextInt();
        }
        FIRST :if (k < size) {
            for (int j = 1; j < size; j++) {
                if (ints[k] == ints[j]) {
                    duplicate = ints[k];
                    break FIRST;
                }
                k++;
            }
            SECOND:for (k = 0; k < size; k++) {
                THIRD: for (int j = 1; j < size; j++) {
                    if (ints[k] == ints[j]) {
                        duplicate = ints[k];
                        break FIRST;
                    }
                }

            }

        }
        System.out.println("First Duplicate is " + duplicate);
    }
}
