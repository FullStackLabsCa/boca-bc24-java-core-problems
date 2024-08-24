package MathematicsProblem;

import java.util.Scanner;
public class DuplicateNumber {
    public static void main(String[] args) {// Find first duplicate in an array of numbers
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers to find the duplicate");
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                char currentValue = input.charAt(i);
                if (currentValue == input.charAt(j)) {
                    System.out.println("Repeated value is  " + currentValue);
                    return;
                }
            }
        }
    }
}

