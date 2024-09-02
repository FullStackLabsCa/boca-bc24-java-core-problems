package problems.problems;

import java.util.Scanner;

public class SubtractWithoutOperator {


    public static void main(String[] args) {
        int a = 27;
        int b = 15;

        while (b != 0) {
            int borrow = (~a & b);
            a = a ^ b;
            b = borrow << 1;
        }
        System.out.println("Difference: " + a);
    }
}

