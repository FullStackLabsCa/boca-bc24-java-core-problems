package problems.calculator_entity.validation_methods;

import java.util.Arrays;

public class AlphabetInputValidation {

    public static void alphabetInputValidation(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                System.out.println("Invalid Input, Alphabets not allowed..");
                break;
            }
        }
    }
}
