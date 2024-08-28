package problems;

import java.util.Scanner;

public class SubtractNumwithoutsubtractingThem {
    public static void main(String[] args) {
        System.out.println("Please Enter the Number to subtract by counting while taking base as 5");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i<5 && i>=0) {
            int sum = 5;
            for (int j = 0; j < i; j++) {
                sum --;
            }
            System.out.println("The resultant value is :" + sum);
        }else
            System.out.println("It cannot be Subtracted");
    }
}