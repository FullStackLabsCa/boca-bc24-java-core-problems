package basic;

import java.util.Scanner;

public class Problem18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a: ");
        StringBuilder factors = new StringBuilder();
        int a = scanner.nextInt();

        for (int i = 1; i <= a; i++) {
            if (a % i == 0) {
                factors.append(i).append(" ");
            }
        }

        System.out.println("Factors of number " + a + " is " + factors );
    }
}
