package problems.string;

/*
Objective: Write a method that removes all vowels from a given string.
        Requirements:
        The method removeVowels(String input) should return the string without any vowels.
        Example Usage:
        removeVowels("Hello World") should return "Hll Wrld" .
        removeVowels("Java Programming") should return "Jv Prgrmmng" .
        Hints:
        Use a loop to iterate through the string and build a new string that excludes vowels.

         */

import java.util.Scanner;

public class RemoveVowels {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine();
        System.out.println("New String without vowel is " + removeVowels(stringInput.toLowerCase()));
    }

    static String removeVowels(String input) {
        String vowel = "aeiou";
        StringBuilder withoutVowel = new StringBuilder(input);
        for (int i = 0; i < vowel.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                if (vowel.charAt(i) == input.charAt(j)) {
                    int i1 = withoutVowel.indexOf(String.valueOf(input.charAt(j)));
                    withoutVowel.deleteCharAt(i1);
                }
            }

        }
        return withoutVowel.toString();
    }
}
