package problems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

    }

    private static boolean isValidInput(String input) {
    }

    public static List<Integer> generateFibonacci(int n) {
        int n=0;
        if(n<=1){
            return n;
        }

        return(Fibonacci(n-1)+Fibonacci)(n-2));

    }
}

