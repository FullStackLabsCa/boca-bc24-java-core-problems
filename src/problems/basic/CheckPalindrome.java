package basic;

import java.util.Scanner;

public class CheckPalindrome {
    public static boolean isPalindrome (String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        boolean isPalindromeFlag = true;
        int start= 0, end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
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

        if (str.isEmpty()) {
            return;
        }
        if (str.length() < 3 && str.length() > 0) {
            System.out.println("Given string " + str +" is a palindrome.");
            return;
        }

        boolean isPalindrome = isPalindrome(str);

        if (isPalindrome) {
            System.out.println("Given string " + str +" is a palindrome.");
        } else {
            System.out.println("Given string " + str +" is not a palindrome.");
        }
    }
}