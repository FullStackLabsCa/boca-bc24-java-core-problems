package advance_calculator;

import java.util.StringTokenizer;

public class InputValidator {
    static boolean isValid = true;

    public static String[] expressionArray(String expression) {
        String stringchar[];
        stringchar = expression.replaceAll("\\s+", "").split("((?=[+\\-*/()^])|(?<=[+\\-*/()^]))|(?=[M])");
        for (int i = 0; i < stringchar.length; i++) {
            if (i<(stringchar.length-1)&& stringchar[i].equals("M") && stringchar[i+1].equals("+")){
                stringchar[i]=stringchar[i]+stringchar[i+1];
                stringchar[++i]=null;
            }
        }
        return stringchar;
    }

    public static boolean expressionChecker(String expression) {
        FIRST:
        if (expression == null || expression.trim().isEmpty()) {
            System.out.println("Error: Input is empty or null");
            isValid = false;
        } else {
            String[] expressionIntoArray = expressionArray(expression);
            for (String a : expressionIntoArray) {
                if (a==null){
                    continue ;
                }else if (a.isBlank()) {
                    System.out.println("Error: Invalid input format");//a-zA-Z
                    isValid = false;
                    break FIRST;
                } else if (a.contains("sqrt")) {
                    isValid = true;
                } else if (a.contains("M+")) {
                    isValid = true;
                } else if (a.matches(".*[a-zA-Z].*")) {
                    System.out.println("Error: Invalid number format");
                    isValid = false;
                    break FIRST;
                }
            }
            int balanceChk = 0;
            for (int brc = 0; brc < expressionIntoArray.length; brc++) {
                if (expressionIntoArray[brc]==null) {
                    continue ;
                } else {
                    if (expressionIntoArray[brc].equals("(")) balanceChk++;
                    else if (expressionIntoArray[brc].equals(")")) {
                        balanceChk--;
                    }
                    if (brc < (expressionIntoArray.length - 1) && (expressionIntoArray[brc].matches(".*[+\\-*].*") && expressionIntoArray[brc + 1] != null && expressionIntoArray[brc + 1].matches(".*[+\\-*].*"))) {
                        System.out.println("Invalid expression.");
                        isValid = false;
                        break;
                    } else if (brc < (expressionIntoArray.length - 1) && (expressionIntoArray[brc].matches(".*[+\\-*^].*") && expressionIntoArray[brc + 1] != null &&expressionIntoArray[brc + 1].matches(".*[+\\-*^].*"))) {
                        System.out.println("Operation not supported.");
                        isValid = false;
                        break;
                    }
                }
            }
            if (balanceChk != 0) {
                isValid = false;
                break FIRST;
            }
        }
        return isValid;
    }
}