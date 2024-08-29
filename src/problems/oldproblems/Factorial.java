package problems.oldproblems;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Please enter a number greater than 1 and less than equals to 12: ");
        int n = s.nextInt();

        int factorial = n;
        while (n != 1) {
            n--;
            factorial *= n;
        }

        System.out.println("Factorial: " + factorial);
    }
}
