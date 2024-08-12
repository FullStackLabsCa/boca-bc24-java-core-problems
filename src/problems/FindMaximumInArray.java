package problems;

import java.util.Scanner;

public class FindMaximumInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fibonacci series: ");
//        String num = scanner.nextLine();

    }
    public static int findMax(int[] num) {
        if(num == null || num.length == 0){
            return Integer.MIN_VALUE;
        }
//        int[] num = {1, 2, 3, 4, 5};
        int max = num[0];
        for (int i = 1; i < num.length; i++) {
            max = Math.max(max, num[i]);
        }
        return max;
    }
}
