package problems.stringproblems;

import java.util.Scanner;
public class CapitalFirstLetter {
    public static String capitalizeWords(String input) {
        StringBuilder combinedString = null;
        String[] splitStringFromUser = input.split(" ");
        StringBuilder buildingNewString = new StringBuilder(" ");
        for (String word : splitStringFromUser) {
            String wordString = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
            combinedString = buildingNewString.append(wordString + " ");
        }
        String newString =String.valueOf(combinedString);
        return newString;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string for first letter capitalization");
        String stringFromUser = scanner.nextLine();
        System.out.println("Here is the desired output: " + CapitalFirstLetter.capitalizeWords(stringFromUser)
        +" ");
    }
}
