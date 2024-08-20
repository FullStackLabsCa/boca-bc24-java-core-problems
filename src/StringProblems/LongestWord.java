package StringProblems;

import java.util.Arrays;
import java.util.Scanner;

public class LongestWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a line the find the longest word");
        String input = scanner.nextLine();
        System.out.println(LongestWord(input));
    }

    public static String LongestWord(String input) {
        String[] values = input.split("\\s+");
        String maxString = "";
        for (String value : values) {
            if (value.length() > maxString.length()) {
                maxString = value;
            }
        }
        return maxString;
    }
}
