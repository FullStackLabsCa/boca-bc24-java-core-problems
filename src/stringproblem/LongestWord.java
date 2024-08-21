package stringproblem;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static stringproblem.CountVowel.countVowels;

public class LongestWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.println("Enter the value to find longest word: ");
        String input = scanner.nextLine();
        String longestWord   = findLongestWord(input);
        out.println(":  "+longestWord);
    }

    public static String findLongestWord(String input) {
        String[] parts = input.split(" ");
        String str = parts[0];
        for (String part : parts) {
            if (str.length() < part.length())
                str = part;
        }
        return str;
    }

    }

