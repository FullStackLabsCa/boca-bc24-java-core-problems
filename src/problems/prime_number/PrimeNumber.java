package problems.prime_number;

import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {

acceptInput(2,10);
acceptInput(4,11);
    }

    private static void acceptInput(Integer firstNumber, Integer lastNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the First Number: ");
        firstNumber = scanner.nextInt();
        System.out.println("Enter the First Number: ");
        lastNumber = scanner.nextInt();
    }
}
