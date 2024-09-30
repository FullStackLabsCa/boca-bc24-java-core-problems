package io.reacticestax.stringsandarraysproblems;//    Find the Longest Word in a String
//    Objective: Write a method that finds and returns the longest word in a string.
//            Requirements:
//    The method  findLongestWord(String input)  should return the longest word in the string.
//    If there are multiple words of the same length, return the first one.
//    Example Usage:
//    findLongestWord("I love programming in Java")  should return  "programming" .
//    findLongestWord("The quick brown fox jumps over the lazy dog")  should return  "quick" .
//    Hints:
//    Use the  split(" ")  method to split the string into words and then iterate through to find the
//    longest one


public class LongestWordInString {

    public static String findLongestWordInString(String inputString ){
    String[] arrOfWords = inputString.split(" ");
 ;

        String longestString= "";

        for (int i = 0; i < arrOfWords.length; i++) {

            if (arrOfWords[i].length() > longestString.length()) {
                longestString = arrOfWords[i];

            }

        }
        System.out.println(longestString);

        return longestString;
    }


}
