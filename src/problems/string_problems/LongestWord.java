package problems.string_problems;

public class LongestWord {
    public static void main(String[] args) {
        System.out.println(LongestWord.findLongestWord("The quick brown fox jumps over the lazy dog"));
        //return should be "jumps"

    }

    public static String findLongestWord(String input) {
        String[] s = input.split(" ");
        String longestWord = s[s.length-1];
        for (int i = s.length-2; i >= 0; i--) {
            if (s[i].length() > longestWord.length()) {
                longestWord = s[i];
            }
        }
        return longestWord;
    }
}