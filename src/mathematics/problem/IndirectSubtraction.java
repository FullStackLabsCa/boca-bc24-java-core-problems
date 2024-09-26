package mathematics.problem;

import java.util.Scanner;

public class IndirectSubtraction {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter second number");
        int sub = 0;
        int secondNumber = scanner.nextInt();
        for (int i = 0; i <= secondNumber; i++) {
            sub = i - firstNumber;
        }
        System.out.println("sum of numbers are : " + sub);
    }
}
