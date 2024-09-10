package problems;

public class PalindromeChecker {
    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        str = str.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();
        int j = str.length() - 1;
        boolean flag = true;
        for (int i = 0; i < str.length() / 2 - 1; i++) {
            if (str.charAt(i) != str.charAt(j)) {
                flag = false;
                break;
            }
            j--;
        }

        return flag;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Madam"));
    }

}


