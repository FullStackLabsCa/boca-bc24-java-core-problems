package mathematicsproblem;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to reverse");
        String string = scanner.nextLine();
        if (string == null) {
            System.out.println("Input is null.");
        } else {
            boolean isPalindrome = isPalindrome(string);
            if (isPalindrome) {
                System.out.println(string + "  Yes, this is pallindrome Value");
            } else {
                System.out.println(string + "  No, this is not Pallindrome Value");
            }
        }
    }

    public static boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }
        String value = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int inputValue = 0;
        int reverseValue = value.length() - 1;
        while (inputValue < reverseValue) {
            if (value.charAt(inputValue) != value.charAt(reverseValue)) {
                return false;
            }
            inputValue++;
            reverseValue--;
        }
        return true;
    }
}

