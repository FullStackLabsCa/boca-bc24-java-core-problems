package problems;

import java.util.Arrays;
import java.util.Scanner;

public class ShiftElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the elements of the array: ");
        for(int i=0;i<size;i++){
            int element = scanner.nextInt();
            arr[i] = element;
        }
        System.out.println("Enter the position of which you want to shift the elements:- ");
        int position = scanner.nextInt();
        System.out.println(Arrays.toString(shiftArray(arr, position)));

    }
    public static int[] shiftArray(int[] array, int positions) {

        int[] newArr = new int[array.length];

        for(int i=0;i<array.length;i++){
            newArr[(i+positions)%array.length] = array[i];
        }
        return newArr;
    }

}
