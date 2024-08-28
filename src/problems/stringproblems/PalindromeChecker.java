package problems.stringproblems;

public class PalindromeChecker {
    public static boolean isPalindrome(String input){
        int length = input.length();
        input = input.toLowerCase();
        String rev = "";
        boolean match = false;
        for(int i = length-1; i>=0; i--){
            rev = rev + input.charAt(i);
        }
        if(input.equals(rev)){
            return true;
        }else{
            return false;
        }
    }
}
