package problems.oldProblems;

import java.util.Scanner;

public class P11_Factor_possible {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter number: ");
        int n = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }

        scanner.close();
    }
}
