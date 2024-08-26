package problems.StringAndArrays;

public class RemoveVowels {
    public static void main(String[] args) {

        String input = "I love programming in Java";
//        String input = "The quick brown fox jumps over the lazy dog";
//        String input = "Java Programming";
//        String input ="";

        System.out.println("Removed Vowels output : "+removeVowels(input));

    }

    private static String removeVowels(String input) {
        if(!input.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (!checkVowelsChar(input.charAt(i))) {
                    stringBuilder.append(input.charAt(i));
                }
            }
            return stringBuilder.toString();
        }
        return "Empty String";

    }

    private static boolean checkVowelsChar(char input) {
        char aChar = 'a';
        char eChar = 'e';
        char iChar = 'i';
        char oChar = 'o';
        char uChar = 'u';

        if (input == aChar || input == eChar || input == iChar || input == oChar || input == uChar) {
            return true;
        } else {
            return false;

        }
    }
}
