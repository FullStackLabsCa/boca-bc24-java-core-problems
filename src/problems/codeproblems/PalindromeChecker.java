package problems.codeproblems;

import java.util.Scanner;

public class PalindromeChecker {

    //import java.util.Scanner -- gets input from the user
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the sentence or a word");
            String userInput = scanner.nextLine();
            String str1  = userInput.toLowerCase().trim();
            boolean isPalindrome = true;
            int stringLength = str1.length();
            for(int i = 0; i < stringLength/2; i++) {
                if (str1.charAt(i) != str1.charAt(stringLength - i - 1)) {
                    isPalindrome = false;
                    break;
                }
            }
            if(isPalindrome){
                System.out.println(str1 + " is a palindrome");
            }
            else {
                System.out.println(str1 + " is not a palindrome");
            }


        }
    }

