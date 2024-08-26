package problems.calculator_entity_oops.validation_methods;

import java.util.StringTokenizer;

public class AlphabetInputValidation {

    public static void alphabetInputValidation(String s) {
//        for (int i = 0; i < s.length(); i++) {
//            if (Character.isLetter(s.charAt(i))) {
//                System.out.println("Invalid Input, Alphabets not allowed..");
//                break;
//            }
//        }
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }
    }
}
