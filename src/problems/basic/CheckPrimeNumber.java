package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckPrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a starting number: ");
        int startNumber = scanner.nextInt();
        System.out.println("Enter a ending number: ");
        int endNumber = scanner.nextInt();

        boolean isValidNumbers = validateNumbers(startNumber, endNumber);
        if (!isValidNumbers) {
            System.out.println("Please enter a valid start and end numbers");
            return;
        }

        List<Integer> primeNumber = primeNumbers(startNumber, endNumber);
        System.out.println("Prime number list is " + primeNumber);

    }

    public static boolean validateNumbers(int startNumber, int endNumber) {
        boolean isValid = true;
        if (startNumber <= 0 || endNumber <= 0) {
            isValid = false;
        } else if (startNumber > endNumber) {
            isValid = false;
        }
        return isValid;
    }

    public static List<Integer> primeNumbers(int startNumber, int endNumber) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = startNumber; i <= endNumber; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeNumbers.add(i);
            }

        }
        return primeNumbers;
    }
}
