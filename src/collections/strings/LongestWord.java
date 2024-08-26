package collections.strings;

public class LongestWord {

    public static String findLongestWord(String input){
        String[] wordsInInput = input.split(" ");

        int maxLength = 0;
        String longestWord = null;

        for(String word : wordsInInput){
            if(word.length() >= maxLength){
                maxLength = word.length();
                longestWord = word;
            }
        }
        return longestWord;
    }

    public static void main(String[] args) {
        System.out.println(LongestWord.findLongestWord("The quick brown fox jumps over the lazy dog"));
    }
}
