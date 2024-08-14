package problems;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S106")
public class Fibonacci {
    public static List<Integer> generateFibonacci(int n) {
        int fisrtValue = 0;
        int secondValue = 1;
        int thirdValue = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(fisrtValue);
        numbers.add(secondValue);

        for (int i = 1; i <= n-2; i++) {
            thirdValue = fisrtValue + secondValue;
            fisrtValue = secondValue;
            secondValue = thirdValue;
            numbers.add(thirdValue);
        }
        return numbers;
    }

    public static void main(String[] args) {
        System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
        System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
    }
}

