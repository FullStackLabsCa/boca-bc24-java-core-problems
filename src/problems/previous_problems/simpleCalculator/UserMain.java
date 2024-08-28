package oldproblems.simpleCalculator;

import java.util.Scanner;

public class UserMain {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please select the option");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus");
        int inp = input.nextInt();

        switch(inp) {
            case 1:
                System.out.println("Please Enter 2 values to add");
                int a1 = input.nextInt();
                int a2 = input.nextInt();
                double ans1 = a1 + a2;
                System.out.println("Addition is: " + ans1);
                break;
            case 2:
                System.out.println("Please Enter 2 values to subtract");
                int b1 = input.nextInt();
                int b2 = input.nextInt();
                double ans2 = b1 - b2;
                System.out.println("Subtraction is: " + ans2);
                break;
            case 3:
                System.out.println("Please Enter 2 values to multiply");
                double c1 = input.nextDouble();
                double c2 = input.nextDouble();
                double ans3 = c1 * c2;
                System.out.println("Multiplication is: " + ans3);
                break;
            case 4:
                System.out.println("Please Enter 2 values to divide");
                double d1 = input.nextDouble();
                double d2 = input.nextDouble();
                if (d2 != 0) {
                    double ans4 = d1 / d2;
                    System.out.println("Division is: " + ans4);
                } else {
                    System.out.println("Division by zero is not allowed.");
                }
                break;
            case 5:
                System.out.println("Please Enter 2 values to find the modulus");
                double e1 = input.nextDouble();
                double e2 = input.nextDouble();
                double ans5 = e1 % e2;
                System.out.println("Modulus is: " + ans5);
                break;
            default:
                System.out.println("Please choose an option from 1 to 5 only");
        }
    }
}
