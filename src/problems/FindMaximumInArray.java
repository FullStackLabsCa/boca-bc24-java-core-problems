package problems;

import java.util.Scanner;

public class FindMaximumInArray {
    public static Object findMax(int[] arr) {
        int max;
        if (arr != null && arr.length!=0) {
            max = arr[0];
//            System.out.println(max);
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) {
                    max = arr[i];
                }
            }
            System.out.println("Maximum number in the array is " + max);
        } else max = Integer.MIN_VALUE;
        return max;
    }

    public static void main(String[] args) {
        System.out.println("Welcome\nEnter the Size of a Array");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int[] arr = {};
        if (a != 0) {
            arr = new int[a];
            System.out.println("Enter the elements of an array");
            for (int i = 0; i < a; i++) {
                arr[i] = sc.nextInt();
            }
            findMax(arr);
        }else System.out.println("Array size is Zero, Enter valid size");
    }
}


