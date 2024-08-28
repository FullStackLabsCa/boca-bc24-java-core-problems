package collection_problems;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BracketBalancingWithoutStack {

    public static boolean validateInput(String userInput){
        boolean isValid =false;
        String[] userInputSplit = userInput.split("");
        String[] restrictedInput ={"{","}","(",")","[","]"};
        FIRST: for (String input: userInputSplit){
//            for (String requiredinput :restrictedInput)
//            if (input.equals(requiredinput)){
//                isValid=true;
//                break;
//            }
            switch (input){
                case "{":
                case "}":
                case "(":
                case ")":
                case "[":
                case "]":
                    isValid=true;
                    break;
                default:
                    break FIRST;
            }
        }
        return isValid;
    }
    public static void bracketChecker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Bracket to check is it balanced");
        String keywordInput = scanner.nextLine();
        if (validateInput(keywordInput)) {
            int keyboardInputLength = keywordInput.length();
            String[] keywordInputArray = keywordInput.split("");
            List<String> bracketsInputList = Arrays.asList(keywordInputArray);
            FIRST:
            if (keyboardInputLength % 2 == 0) {
                for (int keywordInputIndex = 0; keywordInputIndex < keyboardInputLength / 2; keywordInputIndex++) {
                    String bracketIndex = bracketsInputList.get(keywordInputIndex);
                    switch (bracketIndex) {
                        case "{":
                            if (bracketsInputList.get(keyboardInputLength - keywordInputIndex - 1).equals("}")) {
                                break;
                            } else {
                                System.out.println("Brackets are not balanced");
                                break FIRST;
                            }
                        case "(":
                            if (bracketsInputList.get(keyboardInputLength - keywordInputIndex - 1).equals(")")) {
                                break;
                            } else {
                                System.out.println("Brackets are not balanced");
                                break FIRST;
                            }
                        case "[":
                            if (bracketsInputList.get(keyboardInputLength - keywordInputIndex - 1).equals("]")) {
                                break;
                            } else {
                                System.out.println("Brackets are not balanced");
                                break FIRST;
                            }
                    }
                }
                System.out.println("Brackets are balanced");
            } else System.out.println("Brackets are not balanced");
        }else System.out.println("Invalid Input");
    }
    public static void main(String[] args) {
        BracketBalancingWithoutStack.bracketChecker();
    }
}
