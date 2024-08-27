package problems;

import java.util.Scanner;

public class CapitalizeFirstLetterOfEachWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please Enter Input String (Enter x/X to exit program): ");
            String input = scanner.nextLine();
            if (input.trim().toLowerCase().matches("x")) {
                return;
            } else if ((input.isEmpty())) {
                System.out.println("Please enter valid input string");
                continue;
            }
            System.out.println("Capitalized string\t:\t" + capitalizeWords(input));
            //System.out.println("Capitalized string\t:\t" + capitalizeWords2(input));
        } while (true);
    }

    static String capitalizeWords(String input) {
        char[] charArray = input.trim().toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                charArray[i + 1] = Character.toUpperCase(charArray[i + 1]);
            }
        }
        return String.valueOf(charArray);
    }


    static String capitalizeWords2(String input) {
        int index = 0;
        char[] inputCharArray = input.toCharArray();
        while (input.indexOf(" ", index) > 0) {
            index = (input.indexOf(" ", index)) + 1;
            inputCharArray[index] = Character.toUpperCase(inputCharArray[index]);
        }
        return String.valueOf(inputCharArray);
    }
}
