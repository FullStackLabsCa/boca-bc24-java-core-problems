package strings;

import java.util.Scanner;

public class CountVowels {
    public static void main(String[] args) {
        String input = getInput();
        if (input.isEmpty()) {
            System.out.print("0");
        } else {
            countVowels(input);
        }
    }

    private static String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        return sc.nextLine().toLowerCase();

    }

    private static void countVowels(String input) {
        int i = 0;
        int vowels = 0;
        while (i < input.length()) {
            if (input.charAt(i) == 'a' || input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u') {
                vowels++;
            }
            i++;
        }
        System.out.println("The Total number of vowels are: " + vowels);
    }
}
