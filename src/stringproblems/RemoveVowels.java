package str;

import java.util.Scanner;

public class RemoveVowels {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(removeVowels(input));

    }

    public static String removeVowels (String input) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u','A', 'E', 'I','O','U'} ;

        for ( int i = 0; i < input.length(); i++){
            for (char vowel : vowels) {
                if (input.charAt(i) == vowel) {
                    input = input.replace(vowel, ' ');
                }
            }
        }

        input = input.replaceAll("[^a-zA-Z0-9]", "");
        return input;
    }
}
