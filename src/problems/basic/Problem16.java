package basic;

import java.util.Scanner;

public class Problem16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a: ");
        int a = scanner.nextInt();
        System.out.println("Enter b: ");
        int b = scanner.nextInt();
        if (b > 0) {
            while (b > 0) {
                a += 1;
                b -= 1;
            }
        } else if (b < 0) {
            while (b < 0) {
                b += 1;
                a -= 1;
            }
        }

        System.out.println("Sum of two numbers is " + a);
    }
}
