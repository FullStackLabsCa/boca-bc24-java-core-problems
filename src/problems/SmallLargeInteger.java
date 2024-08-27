package problems;

import java.util.Arrays;
import java.util.Scanner;

public class SmallLargeInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the elements of the array: ");
        for(int i=0;i<size;i++){
            int element = scanner.nextInt();
            arr[i] = element;
        }
        System.out.println("The minimum and maximum numbers from array is "+Arrays.toString(findMinMax(arr)));
    }
    public static int[] findMinMax(int[] array) {
        int min = array[0];
        int max = 0;
        int[] minMax = new int[2];
        for (int i : array) {
            if (i >= max) {
                max = i;
            }
        }
        for (int i : array) {
            if (i <= min) {
                min = i;
            }
        }

        minMax[0] = min;
        minMax[1] = max;

        return minMax;
    }
}
