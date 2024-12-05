package problems.stringproblems;

import java.util.Scanner;

public class StringVowelsRemoval {
    public static String removeVowels(String input) {
        String inputLowerCase = input.toLowerCase();
        StringBuilder buildingNewString = new StringBuilder("");
        for(int i = 0; i < inputLowerCase.length(); i++){
            char charValue = inputLowerCase.charAt(i);
            if(charValue != 'a' && charValue != 'e' && charValue != 'i' && charValue != 'o' && charValue != 'u')
                buildingNewString.append(charValue);

        }
        String newString = buildingNewString.toString();

        return newString;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string");
        String userStringInput = scanner.nextLine();
        System.out.println("String without vowels is: " + StringVowelsRemoval.removeVowels(userStringInput));
    }















}
