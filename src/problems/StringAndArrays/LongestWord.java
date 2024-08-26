package problems.StringAndArrays;

public class LongestWord {
    public static void main(String[] args) {

        String input = "I love programming in Java";
//        String input = "The quick brown fox jumps over the lazy dog";
//        String input = "Java Programming";
//        String input ="";

        System.out.println("Longest Word :" + findLongestWord(input));
    }

    private static String findLongestWord(String input) {
        String longString = "";
        int longestStringCount = 0;
        if (!input.isEmpty()) {
            String[] longWords = input.split(" ");
            longestStringCount = longWords[0].length();
            longString = longWords[0];

            for (String word : longWords) {
                if (longestStringCount <= word.length()) {
                    longString = word;
                    longestStringCount = word.length();
                }
            }
            return longString;
        }
        return "Empty String";
    }
}
