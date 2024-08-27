package problems;

import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string: ");
        String input = scanner.nextLine();

        boolean result = isPalindrome(input);

        if (result) System.out.println("Your Input is a Palindrome");
        else System.out.println("Your Input is NOT a Palindrome");
    }

    public static boolean isPalindrome(String input){

        if (input == null) return false;

        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int start = 0;
        int end = normalized.length() - 1;

        while(start < end) {

            if (normalized.charAt(start) != normalized.charAt(end)) return false;

            start = start +1;
            end--;
        }

        return true;
    }
}
