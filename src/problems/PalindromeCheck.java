package problems;

import java.util.Scanner;

public class PalindromeCheck {
    public static void main(String[] args) {
        //input the string
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the String: ");
        String input= scanner.nextLine();
        //normalized the palindrome
        String normalizedString = input.replaceAll("[a-zA-O9]", "").toLowerCase();

        //check the palindrome
        boolean isPalindrome =true;
        int length= normalizedString.length();
        for (int i=0;i<length/2;i++)
        {
            if(normalizedString.charAt(i)!= normalizedString.charAt(length-i-1)){
                isPalindrome=false;
                break;
            }
        }
        //output the result
        if (isPalindrome){
            System.out.println("The string is palindrome . ");
        }else {
            System.out.println("The string is not palindrome ");
        }
        scanner.close();
    }
}