package arrayproblem;

import java.util.Scanner;

public class FindMinMax {
    static String findMinMax(int [] intArray) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < min) {
                min = intArray[i];
            }
            if (intArray[i] > max) {
                max = intArray[i];
            }
        }
        System.out.println("Min value==" + min);
        System.out.println("Max value==" + max);

        return "[" + min + ", " + max + "]";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an array length::");
        int size = scanner.nextInt();
        System.out.println("Enter an array element::");
        int [] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Minimum and Maximum value is = " + findMinMax(arr));
    }
}
