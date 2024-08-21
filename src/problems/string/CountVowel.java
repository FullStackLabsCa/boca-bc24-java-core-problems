package problems.string;

import java.util.Scanner;

public class CountVowel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to count number of vowels in it: ");
        String s= scanner.nextLine().trim().toLowerCase();

        System.out.println("There are " + countVowels(s) + " vowels in the given string.");
    }

    public static int countVowels(String input){
        int count= 0;

        if(input == "" || input.isEmpty()){
            System.out.println("Empty string not allowed." + "\n");
        }
        else {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'a' || input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u') {
                    count = count + 1;
                }
            }
        }
        return count;
    }
}
