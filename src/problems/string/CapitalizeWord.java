package problems.string;
/*
Capitalize the First Letter of Each Word
        Objective: Write a method that capitalizes the first letter of each word in a string.
        Requirements:
        The method capitalizeWords(String input) should return the string with each word's first
        letter capitalized.
        Example Usage:
        capitalizeWords("hello world") should return "Hello World" .
        capitalizeWords("java programming language") should return "Java Programming
        Language" .
        Hints:
        Split the string into words, capitalize each word, and then join them back together.


 */

import java.util.Scanner;

public class CapitalizeWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine();
        capitalizeWords(stringInput);
        System.out.println("Capital words are " + capitalizeWords(stringInput));
    }

    static String capitalizeWords(String input) {
        String capitalString = "";
        StringBuilder stringBuilder = new StringBuilder();
        String[] stringsArray = input.split(" ");
        for (int i = 0; i < stringsArray.length; i++) {
            /* stringBuilder Method
            char c = stringsArray[i].charAt(0);
            stringBuilder.append(Character.toUpperCase(c));
            stringBuilder.append(stringsArray[i].substring(1));
            stringBuilder.append(" ");
             */
            capitalString +=  Character.toUpperCase(stringsArray[i].charAt(0)) + stringsArray[i].substring(1).toLowerCase() + " ";
        }
//        return stringBuilder.toString();
        return capitalString;
    }
}
