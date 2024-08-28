package problems.problems;

import java.util.Scanner;

public class Factors {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number to get the factors: ");
        String n = input.nextLine();

        int number = validator(n);

        factors(number);
    }

    public static int validator(String n) {
        try {
            if (n != null && n.length() > 0) {
                n = n.replace(" ", "");
                return Integer.parseInt(n);
            } else {
                System.out.println("Enter a valid integer:");
                return 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer");
            return 0;
        }
    }

    private static void factors(int n) {
        int i = 1;
        while (i < n) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
            i += 1;
        }
        System.out.println();
    }
}
