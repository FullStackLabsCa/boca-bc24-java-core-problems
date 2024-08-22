package problems.string;


//Write a Java program that counts the number of vowels in a given string.
//        Requirements:
//        The program should define a method countVowels(String input) that takes a string as input and
//        returns the number of vowels (a, e, i, o, u) in the string.
//        The method should be case-insensitive, meaning it should count both uppercase and lowercase
//        vowels (e.g., 'A' and 'a').
//        The program should also handle an empty string input by returning 0.
//        Example Usage:
//        countVowels("Hello World") should return 3 .
//        countVowels("Java Programming") should return 5 .
//        countVowels("Aeiou") should return 5 .
//        countVowels("") should return 0 .

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CountVowels {
    static int counter = 0;
    static ArrayList<Character> vowelCharacters = new ArrayList<>();

    static Character[][] vowelArray = new Character[][]{
            {'a', '0'},
            {'e', '0'},
            {'i', '0'},
            {'o', '0'},
            {'u', '0'},
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine();
        System.out.println("Please enter the string or exit from program");
        if (stringInput.equals("exit")) return;
        countVowels(stringInput.trim().toLowerCase());
        System.out.println("Number of vowels " + counter);
        System.out.println("Repeated vowels " + Arrays.toString(vowelCharacters.toArray()));

        // Print the entire 2D array
        for (int i = 0; i < vowelArray.length; i++) {
            for (int j = 0; j < vowelArray[i].length; j++) {
                System.out.print(vowelArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int countVowels(String input) {

        for (int j = 0; j < input.length(); j++) {
            if (input.charAt(j) == 'a' || input.charAt(j) == 'e' || input.charAt(j) == 'i' || input.charAt(j) == 'o' || input.charAt(j) == 'u') {
                vowelCharacters.add(input.charAt(j));
                switch (input.charAt(j)) {
                    case 'a':
                        vowelArray[0][1]++;
                        break;
                    case 'e':
                        vowelArray[1][1]++;
                        break;
                    case 'i':
                        vowelArray[2][1]++;
                        break;
                    case 'o':
                        vowelArray[3][1]++;
                        break;
                    case 'u':
                        vowelArray[4][1]++;
                        break;
                }

                counter++;
            }
        }
        return counter;
    }
}
