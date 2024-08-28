package advance_calculator;

import java.util.StringTokenizer;

public class InputValidator {
    static boolean isValid = true;
    public static String[] expressionArray(String expression) {

        String stringchar[];
        stringchar = expression.replaceAll("\\s+","").split("((?=[+\\-*/()])|(?<=[+\\-*/()]))");
        return stringchar;
    }

    public static boolean expressionChecker(String expression) {
        FIRST:
        if (expression == null || expression.trim().isEmpty()) {
            System.out.println("Error: Input is empty or null");
            isValid = false;
        } else {
            String[] expressionIntoArray = expressionArray(expression);
//            if (stringchar.length == 3) {
                for (String a : expressionIntoArray) {
                    if (a.isBlank()) {
                        System.out.println("Error: Invalid input format");//a-zA-Z
                        isValid = false;
                        break FIRST;
                    } else if (a.matches(".*[a-zA-Z].*")) {
                        System.out.println("Error: Invalid number format");
                        isValid = false;
                        break FIRST;
                    }
                }
//            } else if (stringchar.length != 3) {
//                System.out.println("Error: Invalid format");
//                isValid = false;
//                break FIRST;
            }
        return isValid;
    }
    }