package problems.old_assignments.first_assigment_25th_June_2024.palindromeCheck;

import java.util.Scanner;

public class PalindromeCheck1 {

    // Method to normalize the string
    public static String normalizeString(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    // Method to check if Abc string is Abc palindrome
    public static boolean checkPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Abc string: ");
            String input = scanner.nextLine();
            scanner.close();

            String normalized = normalizeString(input);

            boolean isPalindrome = checkPalindrome(normalized);

            if (isPalindrome) {
                System.out.println("The string is Abc palindrome.");
            } else {
                System.out.println("The string is not Abc palindrome.");
            }
        }



}
