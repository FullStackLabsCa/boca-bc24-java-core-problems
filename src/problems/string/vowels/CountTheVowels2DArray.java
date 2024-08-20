package problems.string.vowels;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class CountTheVowels2DArray {
    public static void main(String[] args) {
        boolean isValueNull = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a text...");
        String value = input.nextLine();

        if (value == "") {
            isValueNull = true;
            System.out.println("Please enter a valid value");
        }

        int numberOfVowels = countVowels(value);
        if (!isValueNull)
            System.out.println("Given values vowels count: " + numberOfVowels);
    }

    public static int countVowels(String input) {
        char[] chArray = input.toLowerCase().toCharArray();
        char[] vowelsArray = {'a', 'e', 'i', 'o', 'u'};

        char[][] repeatedVowels2DArray = new char[5][2];

        int count = 0;

        for (char ch : chArray) {
            for (char vowel : vowelsArray) {
                if (ch == vowel) {
                    System.out.println("vowel array: " + ch);
                    count++;
                }
            }
        }

        return count;
    }
}
