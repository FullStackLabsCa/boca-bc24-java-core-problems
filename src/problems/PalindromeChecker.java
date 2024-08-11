package problems;


public class PalindromeChecker {
    public static boolean isPalindrome(String str){
        if(str == null){
            return false;
        }
        String newstr = str.replaceAll("[,\\s]","").toLowerCase();
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
