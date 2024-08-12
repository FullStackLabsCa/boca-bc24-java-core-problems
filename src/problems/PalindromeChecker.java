package problems;

import java.util.Scanner;

public class PalindromeChecker {


    public static boolean isPalindrome(String s) {
//        if (s == null) {
//            return false;
//        }

        // Normalize the string: remove non-alphanumeric characters and convert to lowercase
        String normalizedStr = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Check if the normalized string is a palindrome
        int length = normalizedStr.length();
        for (int i = 0; i < length / 2; i++) {
            if (normalizedStr.charAt(i) != normalizedStr.charAt(((length-1)-i))) {
                System.out.println("The given String " + " '" + s + "'" + " is not the Palindrome. ");
                return false;
            }
        }
        System.out.println("The given String " + " '" + s + "'" + " is the Palindrome. ");
        return true;
    }

//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a string: ");
//        String input = scanner.nextLine();
//        isPalindrome(input);
//    }

}
