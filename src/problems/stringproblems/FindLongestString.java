package stringproblems;

import java.util.Scanner;

public class FindLongestString {
    static boolean isValidateInputString(String str) {
        return str.isEmpty() || str == null;
    }

    static String findLongestWord(String inputStr) {
        String [] strAry = inputStr.split(" ");
        String longestString = "";
        int maxLength = 0;
        for (int i = 0; i < strAry.length; i++) {
            if (strAry[i].length() >= maxLength) {
                maxLength = strAry[i].length();
                longestString = strAry[i];
            }
        }
        return longestString;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:: ");
        String str = scanner.nextLine();
        boolean isValidInput = isValidateInputString(str);
        if (isValidInput) {
            return;
        } else {
            System.out.println("String without vowel is= " + findLongestWord(str));
        }
    }
}

