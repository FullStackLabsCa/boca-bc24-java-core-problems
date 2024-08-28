package problems;

import java.util.Scanner;

// Write a Java program to display fibonacci series.


class FibonacciSeries {
    public static void main(String[] args)
    {
        System.out.println("Welcome\nEnter the number of term in fibonacci series");
        Scanner sc = new Scanner(System.in);
        int term = sc.nextInt();
        int a=0,b=1,c;
        for (int i=0;i<=term;i++)
        {
            System.out.print(a+" ");
            c=a+b;
            b=a;
            a=c;
        }
    }
}