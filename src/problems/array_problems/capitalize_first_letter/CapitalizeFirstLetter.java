package problems.array_problems.capitalize_first_letter;

import java.util.Scanner;

public class CapitalizeFirstLetter {
    public static void capitalizeWords(String input){
        String[] wordsArray = input.split(" ");
        StringBuilder newSentence = new StringBuilder();

        for(String arr : wordsArray){
            newSentence.append(arr.substring(0, 1).toUpperCase())
                    .append(arr.substring(1))
                    .append(" ");
        }
        System.out.println(newSentence.toString().trim());

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string:");
        String inputString = input.nextLine();
        capitalizeWords(inputString);
    }
}
