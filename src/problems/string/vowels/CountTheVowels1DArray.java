package problems.string.vowels;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class CountTheVowels1DArray {
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
        char[] chArray = input.toCharArray();
        char[] vowelsArray = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

        char[] repeatedVowels1DArray = new char[input.length()];

        int count = 0;

        for (char ch : chArray) {
            for (char vowel : vowelsArray) {
                if (ch == vowel) {
                    repeatedVowels1DArray[count] = ch;
                    count++;
                }
            }
        }

        char[] removedNullValuesFromRepeatedVowels1DArray = new char[count];

        for (int i = 0; i < count; i++) {
            System.out.println("vowel array " + i+" : " + repeatedVowels1DArray[i]);
            removedNullValuesFromRepeatedVowels1DArray[i] = repeatedVowels1DArray[i];
        }

        return count;
    }
}
