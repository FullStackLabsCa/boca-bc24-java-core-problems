package stringproblems;

import java.util.Scanner;

public class CapitalizeFirstLetter {
    static boolean isValidateInputString(String str) {
        return str.isEmpty() || str == null;
    }

    static String capitalizeWords(String inputStr) {
        String stringArray [] = inputStr.split(" ");
        StringBuilder capitalizeString = new StringBuilder();
        for (int i = 0; i < stringArray.length; i++) {
            capitalizeString.append(stringArray[i].substring(0, 1).toUpperCase()).append(stringArray[i].substring(1)).append(" ");
        }

        return capitalizeString.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:: ");
        String str = scanner.nextLine();
        boolean isValidInput = isValidateInputString(str);
        if (isValidInput) {
            return;
        } else {
            System.out.println("String without vowel is= " + capitalizeWords(str));
        }
    }
}
