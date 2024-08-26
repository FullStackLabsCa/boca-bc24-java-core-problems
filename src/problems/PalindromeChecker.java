package problems;

public class PalindromeChecker {

    public static boolean isPalindrome(String input) {
        if (input != null) {
            String reversedString = "";
            for (int i = 0; i < input.length(); i++) {
                reversedString = (input.charAt(i)) + reversedString;
            }
            if (formatString(input).equalsIgnoreCase(formatString(reversedString))) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
    public static String formatString(String input){
        return input.toLowerCase().replaceAll("[^A-Za-z0-9]","");
    }
}
