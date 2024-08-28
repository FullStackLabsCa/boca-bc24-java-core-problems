package problems;

import java.util.Scanner;

public class Factors {
    public static void main(String[] args) {
        System.out.println("Please enter the number to find the factors");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.print("factors are :");
       for (int i=1;i<=num;i++){
           if (num%i==0){
               System.out.print(i);
               if (i<num) System.out.print(" , ");
           }
       }
        System.out.println(" .");
    }
}