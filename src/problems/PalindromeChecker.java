package problems;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {

        System.out.println("Input a string:");
        Scanner input = new Scanner(System.in);
        String stringInput = input.nextLine();  // Use nextLine() to allow spaces in input
        System.out.println("User input: " + stringInput);


        if (isPalindrome(stringInput)) {
            System.out.println("The given string is a palindrome.");
        } else {
            System.out.println("The given string is not a palindrome.");
        }
    }

    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        // Normalize the input: Remove non-alphanumeric characters and convert to lowercase
        String filteredString = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();


        if (filteredString.length() == 0) {
            return true;
        }


        String rev = "";
        for (int i = 0; i < filteredString.length(); i++) {
            char ch = filteredString.charAt(i);
            rev = ch + rev;
        }


        return filteredString.equals(rev);
    }
}











