package problems;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        isPalindrome(userInput);
    }

    public static boolean isPalindrome(String input) {

        if (input == null) return false;
        if (input.isEmpty()) return true;
        String desiredInput = input.replaceAll("[ ,]", "").toLowerCase();
        int matchedWords = 0;
        int backwardCounter = desiredInput.length() - 1;
        for (int i = 0; i < desiredInput.length() / 2; i++) {
            if (desiredInput.charAt(i) == desiredInput.charAt(backwardCounter)) {
                backwardCounter--;
                matchedWords++;
            }

        }
        return matchedWords > 0;
    }

}
