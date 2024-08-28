package problems.problems;

import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number for the factorial: ");
        int input = Integer.parseInt(scanner.nextLine());
        if (input >= 1 && input <= 100) {
            int fibonacci_a = 0;
            int fibonacci_b = 1;

            int cnt=0;
            while (cnt<input) {
                if (cnt <= 1) {
                    System.out.print(cnt);
                }
                if (cnt > 1) {
                    int fibonacci= fibonacci_a + fibonacci_b;
                    System.out.print(fibonacci);
                    fibonacci_a = fibonacci_b;
                    fibonacci_b = fibonacci;
                }
                cnt = cnt + 1;

            }
        }
        else {
            System.out.println("VAlue is not between 1 to 100 ");
        }
    }
}