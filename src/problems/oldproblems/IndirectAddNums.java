package problems.oldproblems;

import java.util.Scanner;

public class IndirectAddNums {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter two numbers to add: ");
        int a = s.nextInt();
        int b = s.nextInt();

        while (b != 0) {
            a++;
            b--;
        }

        System.out.println("Sum of two numbers without directly adding them is: " + a);
    }
}
