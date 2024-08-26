package problems.oldproblems.FindMaxInArr;

import java.util.Scanner;

public class FindMaxInArr {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the count of number you want to input: ");
        int size = s.nextInt();
        System.out.println("Please enter the numbers: ");
        int[] arr = new int[size];

        for(int i=0; i<size; i++){
            arr[i] = s.nextInt();
        }

        int max = arr[0];

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        System.out.println("Maximum number is: " + max);
    }
}
