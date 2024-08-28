package problems.stringproblems;

import java.util.Scanner;

public class LongestWord {
    public static String findLongestWord(String input) {
        String[] userSentenceSplit = input.split(" ");
        String longestWordInString = "";
        for (String word : userSentenceSplit) {
            if (word.length() >= longestWordInString.length()) {
                longestWordInString = word;
            }
        }
        return longestWordInString;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your sentence");
        String userSentence = scanner.nextLine();
        System.out.println("The longest word in your sentence is: " + LongestWord.findLongestWord(userSentence));
    }
}

