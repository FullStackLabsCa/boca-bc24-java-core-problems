package problems.calculator_entity.validation_methods;

import java.util.Arrays;

public class AlphabetInputValidation {

    public static String alphabetInputValidation(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                break;
            }
        }System.out.println("Invalid Input, Alphabets not allowed..");
        return "Invalid Input, Alphabets allowed..";
    }
}
