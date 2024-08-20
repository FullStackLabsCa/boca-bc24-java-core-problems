package problems.string.vowels;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class RemoveVowels {
    public static void main(String[] args) {
        boolean isValueNull = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a text...");
        String value = input.nextLine();

        if (value == "") {
            isValueNull = true;
            System.out.println("Please enter a valid value");
        }

        String withoutVowelsStr = removeVowels(value);
        if (!isValueNull)
            System.out.println("Remove Vowels from a String: " + withoutVowelsStr);
    }

    public static String removeVowels(String input) {
        char[] chArray = input.toCharArray();
        char[] vowelsArray = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        String str = "";
        boolean isCharVowel;

        for (char ch : chArray) {
            isCharVowel = false;
            for (char value : vowelsArray) {
                if (ch == value)
                    isCharVowel = true;
            }
            if (!isCharVowel)
                str += ch;
        }
        return str;
    }
}
