package stringproblems;

import java.util.Scanner;

public class LongestWord {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        findLongestWord(user);

    }

    public static String findLongestWord(String input) {

        String[] words = input.split(" ");
        String out = words[0];

        for (int i = 0; i < words.length; i++) {
            if(out.length() < words[i].length()) {
                out = words[i];
            }
        }

        System.out.println(out);
        return out;
    }
}
