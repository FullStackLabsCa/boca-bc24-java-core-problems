package arrayproblems;

import java.util.Scanner;

public class CapitalizeArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String input = scanner.nextLine();

        System.out.println("Capitalized String is: "+capitalizeWords(input));
    }
    public static String capitalizeWords(String input) {
        String capitalString = "";
        String[] words = input.split(" ");

        for (String word : words) {
            String character0 = String.valueOf(word.charAt(0)).toUpperCase();
            word = word.replaceFirst(String.valueOf(word.charAt(0)), character0);

            capitalString = capitalString.concat(word + " ");
        }
        return capitalString;
    }
}
