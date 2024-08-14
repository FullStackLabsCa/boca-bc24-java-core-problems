package problems;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class PalindromeChecker {
    public static boolean isPalindrome(String madam) {
        if(madam == null)
            return false;
        String reverseValue = "";
        String filteredValue = "";
        if (!madam.isEmpty() || !madam.isBlank()) {
            filteredValue= madam.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
            for(int i=0;i< filteredValue.length(); i++){
                char ch = filteredValue.charAt(i);
                reverseValue = ch + reverseValue;
            }
        }

        return  (filteredValue.equals(reverseValue)) ? true : false;
    }

    public static void main(String[] args) {
        System.out.println("Please enter a value: ");
        Scanner value = new Scanner(System.in);
        String inputValue = value.next().toLowerCase();

        boolean isEnteredValuePalindrome =isPalindrome(inputValue);

        System.out.println(isEnteredValuePalindrome ? "Enter value is Palindrome" : "Enter value is not Palindrome");
    }
}
