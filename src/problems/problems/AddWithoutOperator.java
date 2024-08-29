package problems.problems;

import java.util.Scanner;

public class AddWithoutOperator {


    public static void main(String[] args) {
        int a = 15;
        int b = 27;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        System.out.println("Sum: " + a);
    }
}
