/*
Find the Longest Word in a String
    Objective: Write a method that finds and returns the longest word in a string.
    Requirements:
        The method findLongestWord(String input) should return the longest word in the string.
        If there are multiple words of the same length, return the first one.
    Example Usage:
        findLongestWord("I love programming in Java") should return "programming" .
        findLongestWord("The quick brown fox jumps over the lazy dog") should return "jumps" .
    Hints:
        Use the split(" ") method to split the string into words and then iterate through to find the
        longest one
*/


package problems.stringProblems;

public class LongestWordString {

    public static void main(String[] args) {

        String str = longestWord("The quick brown fox jumps over the lazy dog");
        System.out.println("The first longest word is: " + str);

    }

    public static String longestWord(String str) {
        int len = 0;
        String longestPart="";
        String[] stringArray = str.split(" ");

        for(String part : stringArray){
            if (len<part.length()){
                longestPart = part;
                len = part.length();
            }
        }

        return longestPart;

    }

}
