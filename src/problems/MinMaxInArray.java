package problems;

import java.util.Scanner;

public class MinMaxInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of array: ");
        int size = scanner.nextInt();

        System.out.println("Enter the elements of array: ");
        int[] arr = new int[size];
        for(int i=0; i<size; i++){
            arr[i] = scanner.nextInt();
        }
        int[] minMax = findMinMax(arr);
        System.out.print("["+minMax[0]+","+minMax[1]+"]");
    }

    public static int[] findMinMax(int[] array){
        int[] minMax = new int[2];

        minMax[0] = array[0];
        minMax[1] = array[0];

        for (int num : array) {
            if (num < minMax[0]) {
                minMax[0] = num;
            }

            if (num > minMax[1]) {
                minMax[1] = num;
            }
        }

        return minMax;
    }
}
