package stringproblems;

import java.util.Scanner;

public class CapitalizeWords {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        capitalizeWords(input);
    }

    public static String capitalizeWords (String input) {

        String[] words = input.split(" ");
        String out = "";

        for (String word : words) {
            out = out + " " + toLowerCase(word);
        }
        System.out.println(out);
        return "";
    }

    public static String toLowerCase(String input) {

                String subWords = input.substring(1,input.length());
                String out = Character.toUpperCase(input.charAt(0)) + subWords;
                return out;
    }
}
