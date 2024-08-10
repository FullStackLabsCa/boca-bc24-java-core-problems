package problems;

public class PalindromeChecker {
    public static void main(String[] args) {
        isPalindrome(null);
    }

    public static boolean isPalindrome(String madam) {
        if (madam == null) {
            System.out.println("Provided null as input");
            return false;
        }
        String clnStr = madam.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        System.out.println("Input String: " + madam);
        System.out.println("Cleaned up Input String: " + clnStr);
        int clnstrLength = clnStr.length();
        for (int i = 0; i < clnstrLength / 2; i++) {
            if (clnStr.charAt(i) != clnStr.charAt((clnstrLength - 1) - i)) {
                System.out.println("String : " + madam + " is Not a Palindrome");
                return false;
            }
        }
        System.out.println("String : " + madam + " is a Palindrome");
        return true;
    }
}
