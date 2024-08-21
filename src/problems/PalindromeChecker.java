package problems;

public class PalindromeChecker {
    public static boolean isPalindrome(String madam) {
if(madam==null)
{
    return false;
}
        String normalizedString = madam.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        //check the palindrome
        boolean isPalindrome = true;
        int length = normalizedString.length();
        for (int i = 0; i < length / 2; i++) {
            if (normalizedString.charAt(i) != normalizedString.charAt(length - i - 1)) {
                isPalindrome = false;
                break;
            }
        }
    return(isPalindrome);
    }

}
