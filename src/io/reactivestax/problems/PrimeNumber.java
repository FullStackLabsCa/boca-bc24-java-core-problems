package io.reactivestax.problems;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
    public static void main(String[] args) {
        System.out.println(PrimeNumber.primeChecker(10, 20));
    }

    public static List<Integer> primeChecker(int rangeStart, int rangeEnd) {
        List<Integer> primeNumber = new ArrayList<>();
        boolean isPrime = true;
        for (int i = rangeStart; i <= rangeEnd; i++) {
            for (int j = 2; j <= i; j++) {
                if (i % j == 0 && i != j) {
                    isPrime = false;
                    break;
                } else {
                    isPrime = true;
                }

            }
            if (isPrime) {
                primeNumber.add(i);
            }

        }
        return primeNumber;
    }
}
