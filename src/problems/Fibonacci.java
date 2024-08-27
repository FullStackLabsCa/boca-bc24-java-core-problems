package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Question 9
public class Fibonacci {

    public static List<Integer> generateFibonacci(int n) {

        List<Integer> series = new ArrayList<>();
        int firstNum = 0, secondNum = 1, count = 2;

        series.add(firstNum);
        series.add(secondNum);

        while (count < n) {
            series.add(count, series.get(count - 1) + series.get(count - 2));
            count++;
        }

        return series;
    }

    public static boolean validateInput(String input) {
        boolean isValid = false, hasAlphabet = false;

        ArrayList alphabets = new ArrayList<>();
        String alphabetsAvailable = "0123456789";
        for (int i = 0; i < alphabetsAvailable.length(); i++) {
            alphabets.add(alphabetsAvailable.charAt(i));
        }
        //check if string has alphabets
        for (int i = 0; i < input.length(); i++) {
            if (!alphabets.contains(input.charAt(i))) {
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
                return false;
            }
        }

        if (Integer.parseInt(input) < 4) {
            // Input too small
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            isValid = false;
        } else if (Integer.parseInt(input) > 47) {
            // Input too big
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            isValid = false;
        } else if (Integer.parseInt(input) == 4) {
            //
            System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
            isValid = false;
        } else
            isValid = true;

        return isValid;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number between 4 and 47: ");
        String input = scanner.nextLine();

        if (Fibonacci.validateInput(input)) {
            List<Integer> listGenerated = Fibonacci.generateFibonacci(Integer.parseInt(input));

            for (int n : listGenerated) {
                System.out.println(n);
            }
        }

        scanner.close();
    }
}