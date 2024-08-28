package problems;

import java.util.Scanner;

public class AddNumwithoutAddThem {
    public static void main (String[] args) {
        System.out.println("Please Enter the Number to add by counting while taking base as 5");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int sum=5;
        for (int j=0;j<i;j++){
            sum ++;
        }
        System.out.println("The resultant value is :"+sum);
    }
}