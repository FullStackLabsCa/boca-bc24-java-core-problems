package problems;

import java.util.Arrays;
import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindrome(String s) {
        boolean equals;
        if (s != null) {
            String temp = s;
            String replaceAll = s.replaceAll("[\\s+,]+", "");
            String sLowerCase = replaceAll.toLowerCase();
            char[] sCharArray = sLowerCase.toCharArray();
            char rev[] = new char[sCharArray.length];
            //        System.out.println(rev.length);
            int i = sCharArray.length - 1;
            for (char c : sCharArray) {
                rev[i] = c;
                i--;
            }
           equals = Arrays.equals(rev, sCharArray);
            if (equals)
                System.out.println("Given String "+s+" is palindrom");
            else
                System.out.println("Given String "+s+" is not a palindrom");
        }else equals=false;
        return equals;
    }
    public static void main(String[] args) {
        System.out.println("Welcome\nTo check whether String is palindrome or not\nEnter the String");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        isPalindrome(s);
    }
}