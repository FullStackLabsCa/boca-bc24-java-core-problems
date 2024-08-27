package previous;

public class PalindromeChecker {
    public static boolean isPalindrome(String madam) {
        if((null == madam)) {
            return false;
        } else if(madam == "") {
            return true;
        }

        String norString ;
        norString = madam.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();

        for(int i = 0; i <= norString.length()/2; i++) {
            if(norString.charAt((norString.length()-1) - i) != norString.charAt(i)) {
                return false;
            }
        }

            return true;
    }
}