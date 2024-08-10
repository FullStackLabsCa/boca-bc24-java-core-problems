package problems;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any string to check Palindrome: ");
        String str = scanner.nextLine();
//        String lowerstr = str.toLowerCase();
//        System.out.println(lowerstr);
    }
    public static boolean isPalindrome(String str){
        String newstr = str.replaceAll("\\s+","");
        newstr = newstr.toLowerCase();
        int l = 0;
        int r = newstr.length()-1;

        while(l < r) {
            if (newstr.charAt(l) != newstr.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
