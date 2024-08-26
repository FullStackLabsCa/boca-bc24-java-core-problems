package problems.array;

import java.util.Scanner;

public class PalindromeCheck {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter String: ");
        String input = reader.nextLine();
        reader.close();


        input = input.replaceAll(" ", "");
        input = input.replaceAll("[^a-zA-Z0-9 ]", "");
        System.out.println(checkPalindrome(input));
    }

    private static String checkPalindrome(String input) {
        String reversedString ="";

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            reversedString = ch+reversedString;
        }
        if(input.equalsIgnoreCase(reversedString)){
            return "Given string is palindrome";
        }else{
            return "Given string is not a palindrome";
        }
    }
}
