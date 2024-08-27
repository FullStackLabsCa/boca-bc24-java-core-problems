//Problem 2: Palindrome Check
//        Objective
//        Write a Java program to check if a given string is a palindrome. A palindrome is a string that reads the same forward and backward, ignoring spaces, punctuation, and case differences.
//
//        Steps to Implement Palindrome Check
//        Input the string: Take a string as input from the user.
//        Normalize the string: Remove non-alphanumeric characters and convert it to lower case.
//        Check for palindrome:
//        Compare characters from the beginning and end of the string moving towards the center.
//        If all corresponding characters match, the string is a palindrome.
//        Output the result: Print whether the string is a palindrome or not.

import java.util.Arrays;
import java.util.Scanner;

public class PalindromeCheck {
    public static void palindromeCheck() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter string");
        String userInput = myObj.nextLine();
        System.out.println(userInput);

        String user = userInput.toLowerCase().replaceAll("[^A-Za-z]", "");
        int counter = 0;
        boolean isPalindrome = true;
        for (int i = 0; i < Math.floor((user.length() / 2)); i++) {
            counter++;
            if (user.charAt(i) != user.charAt(user.length() - counter)) {
                isPalindrome = false;
                break;
            }
        }
        if (isPalindrome) {
            System.out.println("The input word " + user + " is  Palindrome");
        } else {
            System.out.println("The input word " + user + " is not  Palindrome");

        }


    }


}

