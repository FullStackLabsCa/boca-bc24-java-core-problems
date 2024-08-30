package problems;

import java.util.Scanner;

public class PalindromeChecker {


    public static boolean isPalindrome(String str) {
        if (str == null) {
            System.out.println("The String is not a Palindrome");
            return false;
        }
        // Normalize the string: remove non-alphanumeric characters and convert to lower case

        String normalizedString = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int length = normalizedString.length();
        for (int i = 0; i < length / 2; i++) {
            if (normalizedString.charAt(i) != normalizedString.charAt(length - i - 1)) {
                System.out.println("The string is not a palindrome.");
                return false;

            }
        }
        System.out.println("The string is a palindrome.");
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String input = scanner.nextLine();
        PalindromeChecker.isPalindrome(input);
        scanner.close();
    }
}
