package problems.string;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class CapitalizeTheFirstLetter {
    public static void main(String[] args) {
        boolean isValueNull = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a text...");
        String value = input.nextLine();

        if (value == "") {
            isValueNull = true;
            System.out.println("Please enter a valid value");
        }

        String longestWord = capitalizeWords(value);
        if (!isValueNull)
            System.out.println("Capitalize Words: " + longestWord);
    }

    public static String capitalizeWords(String input) {
        String[] parts = input.split(" ");
        String str = "";

        for (String part : parts) {
//            String convertToLowerCase = part.toLowerCase();
//            for (int i = 0; i < convertToLowerCase.length(); i++) {
//                if (i == 0)
//                    str += String.valueOf(convertToLowerCase.charAt(0)).toUpperCase();
//                else
//                    str += convertToLowerCase.charAt(i);
//            }


            str = str + Character.toUpperCase(part.charAt(0)) + part.substring(1).toLowerCase() + " ";
        }
        return str;
    }
}
