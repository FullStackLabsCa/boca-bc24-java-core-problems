package problems;

public class PalindromeChecker {

    public static boolean isPalindrome(String s) {

        if (s == null) {
            return false;
        }
        String regex = "[^A-Za-z0-9]+";

        s = s.replaceAll(regex, "");

//        System.out.println("Normalized String is: "+ s);
        char[] c = s.toLowerCase().toCharArray();


        int lastIndex = s.length() - 1;


        for (int firstIndex = 0; firstIndex < s.length() / 2; firstIndex++) {
            if (c[firstIndex] != c[lastIndex]) {
                return false;
            }
            lastIndex--;
        }
        return true;
    }
}
