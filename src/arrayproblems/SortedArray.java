package arrayproblems;

import java.util.Scanner;

public class SortedArray {
    public static void main(String[] args) {
//        int[] anArray = {4,3,2,1};
        System.out.println("Is the array Sorted: "+isSorted(new int[]{1, 2, 3, 4}));
        System.out.println("Is the array Sorted: "+isSorted(new int[]{4,3,2,1}));
    }

    public static boolean isSorted(int[] array) {
        if (array == null || array.length <= 1) {
            return true;
        }

            boolean ascending = true;


            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    ascending = false;
                    break;
                }
            }

            return ascending;
        }
    }

