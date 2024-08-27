package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a array length: ");
        int araryLength = scanner.nextInt();
        int [] arr = new int[araryLength];
        List<Integer> duplicateArr = new ArrayList<>();
        System.out.println("Enter elements of array: ");
        for (int i = 0; i < araryLength; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 0; i < araryLength; i++) {
            if (duplicateArr.contains(arr[i])) {
                System.out.println("First Duplicate element is = " + arr[i]);
                return;
            } else {
                duplicateArr.add(arr[i]);
            }
        }
    }
}
