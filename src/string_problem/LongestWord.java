package string_problem;

import java.util.Scanner;

public class LongestWord {
    public static String StringLongestWord(String input) {
        String longestWord="";
        int lengthOfWord=0;
        String[] splitStringArray = input.split(" ");
        for (String longWord:splitStringArray) {
            int wordLength = longWord.length();
            if (lengthOfWord<=wordLength){
                longestWord=longWord;
                lengthOfWord = wordLength;
            }
        }
        return longestWord;
    }

    public static void main(String[] args) {
        System.out.println("Find the longest Word in provided String");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Longest word in the String : ");
        System.out.print(StringLongestWord(input));
    }
}