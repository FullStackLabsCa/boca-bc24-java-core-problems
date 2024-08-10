package problems;

import java.util.Scanner;

public class FindMaximumInArray {
//    public static void main(String[] args) {
////
//    }
    public static int findMax(int[] num) {
//        int[] num = {1, 2, 3, 4, 5};

        int max = num[0];
        for (int i = 1; i < num.length; i++) {
            max = Math.max(max, num[i]);
        }
        return max;
    }
}
