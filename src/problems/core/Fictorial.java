package problems.core;

import java.util.Scanner;

public class Fictorial {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter num");
        int term=sc.nextInt();


        int sum=1;
        for(int i=term; i>=1; i--) {
            sum = sum * i;

            System.out.println(i);
        }

            System.out.println("Total "+ sum);


    }
}
