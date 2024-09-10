package problems;

import problems.string.Reversestring;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(Reversestring[] args) {
        int a=0;
        int b=1;
        int sum;
        System.out.print(a +" "+b+" ");
        //System.out.println(b);
        for(int i=1; i<=8;i++){

            ;
            sum=a+b;
            System.out.print(sum+" ");
            a=b;
            b=sum;
            //   System.out.println(sum);
        }
    }
}

