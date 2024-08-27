package problems;

import java.util.Scanner;

public class CountAlphabetOrNumRepeated {
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine();
        inputString = inputString.toLowerCase();
        int count = 0;

        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (Character.isLetterOrDigit(currentChar)) {
                int charCount = 0;

                for (int j = 0; j < inputString.length(); j++) {
                    if (currentChar == inputString.charAt(j)) {
                        charCount++;
                    }
                }
                System.out.println(currentChar + " : " + charCount);

                if (charCount > 1) {
                    count++;


                    // inputString = inputString.replace(currentChar, '\0');
                }
            }
        }


    }

}
