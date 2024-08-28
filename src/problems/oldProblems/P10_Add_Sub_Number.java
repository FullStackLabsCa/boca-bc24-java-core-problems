package problems.oldProblems;

import java.util.Scanner;

public class P10_Add_Sub_Number {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a: ");
        int a = scanner.nextInt();

        System.out.println("Enter b: ");
        int b = scanner.nextInt();

        System.out.print("Select operation - 1 for Addition, 2 for Subtraction: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            int result = addition(a, b);
            System.out.println("The result of addition is: " + result);
        } else if (choice == 2) {
            int result = subtraction(a, b);
            System.out.println("The result of subtraction is: " + result);
        } else {
            System.out.println("Invalid choice! Please select 1 for Addition or 2 for Subtraction.");
        }


        scanner.close();
    }

    public static int addition(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            a++;
            b--;
        }

//        System.out.println("The sum is " + a);
        return a;
    }

    public static int subtraction(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b != 0) {
            a--;
            b--;
        }

//        System.out.println("The sum is " + a);
        return a;

    }

}


/*

Swapping numbers without using 3rd variables:
        int a=5, b=9;
        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("a: "+ a + " "+"b: " + b);
 */

