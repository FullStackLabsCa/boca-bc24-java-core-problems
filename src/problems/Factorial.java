package problems;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("Enter the number to calculate factorial:");
        Scanner scanner =new Scanner(System.in);
        int n = scanner.nextInt();
        int temp=1, fact=0;
        if (n<=12 && n>0){
         for (int i=n;i>0;i--) {
             fact= temp*i;
             temp =fact;
         }
         System.out.println("Factorial of "+n+" is "+fact);
     }else
            System.out.println("Please enter value between 1 to 12");
    }
}