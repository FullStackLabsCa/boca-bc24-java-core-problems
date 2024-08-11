package problems;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word to check whether it is Palindrome or not: ");
        String word = scanner.nextLine();

        if (isPalindrome(word)){
            System.out.println("String is Palindrome.");
        }
        else{
            System.out.println("String is not Palindrome.");
        }
    }

    public static boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        word =word.replaceAll(" ", "");
        word = word.replaceAll(",", "");
        word = word.toLowerCase();
        String rev = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            rev = rev + word.charAt(i);
        }
        return word.equals(rev);
    }
}