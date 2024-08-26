package problems.oldproblems.PrimeNumbersInRange;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumbersInRange {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean flag = false;

        while (!flag) {
            System.out.println("Enter the first number: ");
            String first = scanner.nextLine().trim();

            System.out.println("Enter the second number: ");
            String second = scanner.nextLine().trim();
            if (!first.matches("\\d+") || !second.matches("\\d+")) {
                System.out.println("Please enter valid numbers.");
            } else {
                int firstNumber = Integer.parseInt(first);
                int secondNumber = Integer.parseInt(second);

                if (firstNumber > secondNumber) {
                    System.out.println("First number cannot be greater than second number");
                } else {
                    System.out.println(checkPrimeNumber(firstNumber, secondNumber));
                    flag = true;
                }
            }
        }
    }

    public static ArrayList<Integer> checkPrimeNumber(int first, int second) {
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        for (int i = first; i <=second; i++) {
            boolean flag = false;
            for (int j=2; j<i; j++){
                if(i%j == 0){
                    flag = true;
                    break;
                }
            }
            if(!flag) primeNumbers.add(i);
        }
        return primeNumbers;
    }
}
