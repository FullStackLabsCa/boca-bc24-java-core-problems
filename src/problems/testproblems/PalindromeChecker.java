package testproblems;

import java.util.Scanner;

public class PalindromeChecker {
    public static boolean isPalindrome(String s) {
        boolean isPalindromeFlag = true;
        if (s == null) {
            return false;
        } else if (s.isEmpty()) {
            return true;
        }
        s = s.replaceAll    ("[^a-zA-Z0-9]", "").toLowerCase();
        int start= 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                isPalindromeFlag = false;
            }
            start += 1;
            end -= 1;
        }
        return isPalindromeFlag;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();

        boolean isPalindromeResult = isPalindrome(str);
        System.out.println(isPalindromeResult);
    }
}
