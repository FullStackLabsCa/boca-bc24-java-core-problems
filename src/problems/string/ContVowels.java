package problems.string;

import java.util.Scanner;

public class ContVowels {
    public static int countVowels(String input) {
        int count = 0;

        String lowerCase = input.toLowerCase();
        char[] characters = lowerCase.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if(characters[i] == 'a' || characters[i] == 'e' || characters[i] == 'i' || characters[i] == 'o' || characters[i] == 'u'){
                count++;
            }
        }
        System.out.println("Number of vowels in given String: "+ count);
        return (count);
    }


    public static void main(String[] args) {
        System.out.println("Enter the String to Count the Vowels....");
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        ContVowels.countVowels(string);
//        ContVowels yyyy = new ContVowels();
//        yyyy.countVowels(string);

    }


}