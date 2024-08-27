package problems;

import java.util.Scanner;

public class SubnetworkNumb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first value: ");
        int firstValue  = scanner.nextInt();
        System.out.println("Enter the second value: ");
        int secondValue = scanner.nextInt();
        int sum=0;
        for (int i=1;i<=secondValue;i++){
            sum = firstValue-i;

        }
        System.out.println(sum);
}
}