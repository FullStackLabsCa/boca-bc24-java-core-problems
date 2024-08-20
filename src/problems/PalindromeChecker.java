package problems;

public class PalindromeChecker {
    public static void main(String[] args) {

    }
    public static boolean isPalindrome (String input){
            if(input==null){
                return false;
            }
        String newInput = input.replaceAll("[,\\s]+", "").toLowerCase();
        String rev = "";
        char ch;
        for (int i = 0; i < newInput.length(); i++) {
            ch = newInput.charAt(i);
            rev = ch + rev;
        }
        System.out.println(newInput);
        return rev.equals(newInput);
    }

}
