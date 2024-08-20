package problems.string;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class LongestWord {
    public static void main(String[] args) {
        boolean isValueNull = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a text...");
        String value = input.nextLine();

        if (value == "") {
            isValueNull = true;
            System.out.println("Please enter a valid value");
        }

        String longestWord = findLongestWord(value);
        if (!isValueNull)
            System.out.println("Longest Word: " + longestWord);
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
