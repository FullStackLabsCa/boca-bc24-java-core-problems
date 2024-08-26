package problems.oldProblems.SumOddEven;

import java.util.Scanner;

public class SumOddEven {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the count of number you want to input: ");
        int size = s.nextInt();
        System.out.println("Please enter the numbers: ");
        int[] arr = new int[size];

        for(int i=0; i<size; i++){
            arr[i] = s.nextInt();
        }
        long oddSum = 0;
        long evenSum =0;

        for(int num: arr){
            if(num%2==0){
                evenSum += num;
            } else{
                oddSum += num;
            }
        }

        System.out.println("Sum of odd numbers: " + oddSum);
        System.out.println("Sum of even numbers: " + evenSum);
    }
}
