package io.reactivestax.collections;

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

    public static void countVowels(String input) {
        int totalVowels = 0;
        char[][] vowels = {
                {'a', '0'},
                {'e', '0'},
                {'i', '0'},
                {'o', '0'},
                {'u', '0'}
        };

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (ch == vowels[j][0]) {
                    vowels[j][1] = (char) (vowels[j][1] + 1);
                    totalVowels++;
                    break;
                }
            }
        }

        for (char[] vowel : vowels) {
            System.out.println(vowel[0] + ": " + vowel[1]);
        }

        System.out.println("The total number of vowels is: " + totalVowels);
    }
}
