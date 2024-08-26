package stringproblems;

import java.util.Scanner;

public class RemoveVowel {
    static boolean isValidateInputString(String str) {
        return str.isEmpty() || str == null;
    }

    static String removeVowels(String inputStr) {
        StringBuilder stringWithoutVowel = new StringBuilder();
        StringBuilder removedVowels = new StringBuilder();
        for (char ch : inputStr.toLowerCase().toCharArray()) {
            switch (ch) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    removedVowels.append(ch);
                    break;
                default:
                    stringWithoutVowel.append(ch);
                    break;

            }
        }

        return "\nString without vowels= " + stringWithoutVowel.toString()
                + "\nRemoved vowels= "+ removedVowels.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:: ");
        String str = scanner.nextLine();
        boolean isValidInput = isValidateInputString(str);
        if (isValidInput) {
            return;
        } else {
            System.out.println("Original string is= " + str + removeVowels(str));
        }
    }
}
