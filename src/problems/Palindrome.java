package problems;

public class Palindrome {

    public static void main(String[] args) {

        String input = "12321";
        boolean result = isPalindrome(input);
        System.out.println("Is palindrome " + result);
    }
    public static boolean isPalindrome(String input){

        if(input == null){
            return false;
        }

        String inputForPalindrome = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = inputForPalindrome.length() - 1;

        while (left<right){
            if(inputForPalindrome.charAt(left) != inputForPalindrome.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

}
