package problems;

import java.util.Scanner;

public class FindingLongestWordInString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please Enter Input String (Enter x/X to exit program): ");
            String input = scanner.nextLine();
            if (input.trim().toLowerCase().matches("x")) return;
            System.out.println("Largest word in the string is\t:\t" + findLongestWord(input));
        } while (true);
    }

    static String findLongestWord(String input) {

        String[] wordsList = input.split(" ");
        String maxString = "";
        for (String s : wordsList) {
            maxString = (maxString.length() >= s.length()) ? maxString : s;
        }
        return maxString;
    }
}
