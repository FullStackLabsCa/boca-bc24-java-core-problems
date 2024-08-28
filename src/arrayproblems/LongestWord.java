package arrayproblems;

import java.util.Scanner;

public class LongestWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String input = scanner.nextLine();
        String str = findLongestWord(input);
        System.out.println("The longest word in the string: "+str );

    }
    public static String findLongestWord(String input) {
        String[] words = input.split(" ");
        String str = words[0];
        for (int i = 0; i < words.length; i++) {
                if (str.length()<(words[i]).length()) {
                 str=words[i];
                }
        }
        return str;
    }
}
