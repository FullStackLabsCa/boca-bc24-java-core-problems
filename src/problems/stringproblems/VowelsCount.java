package problems.stringproblems;

import java.util.Scanner;

public class VowelsCount {
    public static int countAllVowels(String input) {
        // valid input only
        int returnValue = 0;
        if(input == " ") {
            System.out.println("invalid input");
        } else { //removing case sensitivity
            String inputLower = input.toLowerCase();
        }

        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                returnValue++;
            }
        }
        return returnValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string");
        String userValue = scanner.nextLine();
        System.out.println(countAllVowels(userValue));
    }



}
