package io.reactivestax.strings;


import java.util.Scanner;

public class RemoveVowels extends CountVowelsUtility {
    public static void main(String[] args) {
        String input = getInput();
        if (input.isEmpty()) {
            System.out.print("0");
        } else {
            removeVowels(input);
        }
    }

    private static String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        return sc.nextLine();
    }

    private static void removeVowels(String input) {
        int i = 0;
        String output = "";
        CountVowelsUtility.countVowels(input.toLowerCase());
        while (i < input.length()) {
            if (input.charAt(i) == 'a' || input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u' || input.charAt(i) == 'A' || input.charAt(i) == 'E' || input.charAt(i) == 'I' || input.charAt(i) == 'O' || input.charAt(i) == 'U') {
            } else {
                output += input.charAt(i);
            }
            i++;
        }
        System.out.println("Original: " + input);
        System.out.println("Output: " + output);
    }
}
