package problems.string;

import java.util.Scanner;

/*
Objective: Write a method that finds and returns the longest word in a string.
        Requirements:
        The method findLongestWord(String input) should return the longest word in the string.
        If there are multiple words of the same length, return the first one.
        Example Usage:
        findLongestWord("I love programming in Java") should return "programming" .
        findLongestWord("The quick brown fox jumps over the lazy dog") should return "jumps" .
        Hints:
        Use the split(" ") method to split the string into words and then iterate through to find the
        longest one.

 */
public class LongestWordInString {
//    static int count = 0;
    static int maxCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine();
//        findLongestWord(stringInput);
        System.out.println("Longest Word is " +  findLongestWord(stringInput));
//        System.out.println("Max Count is " +  count);
    }

    static String findLongestWord(String input){
        String longestWord = "";
        String[] wordsFromInputString = input.split(" ");
        for (int i = 0; i <wordsFromInputString.length ; i++) {
            int i1 = countCharacterInString(wordsFromInputString[i]);
            if(i1> maxCount) {
                longestWord = wordsFromInputString[i];
                maxCount = i1;
            }

        }

        return longestWord;

    }

    static int countCharacterInString(String stringInput){
        int count = 0;
        for(int i = 0; i < stringInput.length(); i++) {
            char ch = stringInput.charAt(i);
            if(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') count++;
        }
        return count;
    }
}
