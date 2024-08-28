/*

Remove Vowels from a String
    Objective: Write a method that removes all vowels from a given string.
    Requirements:
        The method removeVowels(String input) should return the string without any vowels.
    Example Usage:
        removeVowels("Hello World") should return "Hll Wrld" .
        removeVowels("Java Programming") should return "Jv Prgrmmng" .
    Hints:
        Use a loop to iterate through the string and build a new string that excludes vowels

*/

package problems.stringProblems;

public class RemoveVowels {

    public static void main(String[] args) {

        String input = "Hello World";
        System.out.println("Original String: " + input);

        String afterOperation = removeVowels(input);
        System.out.println("After removing vowels: " + afterOperation);

    }

    private static String removeVowels(String input) {


//        Solution - 1
        String newString = input.toLowerCase();
        return newString.replaceAll("[aeiou]", "");

//        Solution - 2
//        String newString = "";
//        for (int i = 0; i < input.length(); i++) {
//
//
//            char ch = input.charAt(i);
//
//            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u' && ch != 'A' && ch != 'E' && ch != 'I' && ch != 'O' && ch != 'U') {
//                newString += ch;
//            }
//
//        }
//        return newString;


    }

}
