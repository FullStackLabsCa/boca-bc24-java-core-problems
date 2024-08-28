package problems.oldProblems;

import java.util.Scanner;

public class P6_Fibonacci {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number between 1 to 100");

        boolean valid = false;

        while (!valid) {

            int n = scanner.nextInt();
            if (n < 1 || n > 100) {
                System.out.println("Please enter number between 1 to 100");
            } else {
                int first = 0, second = 1;
                System.out.print("Fibonacci Series: ");
                for (int i = 1; i <= n; i++) {
                    System.out.print(first + " ");

                    int next = first + second;
                    first = second;
                    second = next;

                }
                valid = true;
            }
        }


        scanner.close();
    }

}
