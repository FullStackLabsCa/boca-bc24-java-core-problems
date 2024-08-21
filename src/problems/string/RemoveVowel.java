package problems.string;

import java.util.Scanner;

public class RemoveVowel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to count number of vowels in it: ");
        String s= scanner.nextLine().toLowerCase();

        System.out.println("String after removing vowels: " + removeVowels(s));
    }

    public static String removeVowels(String input){
        if(input== "" || input.isEmpty()){
            System.out.println("Empty string not allowed." + "\n");
            return null;
        }
        else {
            return input.replaceAll("[aeiou]", "");
        }
    }
}
