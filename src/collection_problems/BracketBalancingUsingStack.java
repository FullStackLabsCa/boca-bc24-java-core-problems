package collection_problems;

import java.util.Scanner;
import java.util.Stack;

public class BracketBalancingUsingStack {
    public static boolean validateInput(String userInput){
        boolean isValid =false;
        String[] userInputSplit = userInput.split("");
        FIRST: for (String input: userInputSplit){
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
    public static boolean bracketChecker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Bracket to check is it balanced");
        String input = scanner.nextLine();
        boolean isBracketedPair = false;
        if (validateInput(input)) {
            Stack<String> stringStack = new Stack<>();
            char characterAt;
            if (input.length() % 2 == 0) {
                for (int bracketArrayIndex = 0; bracketArrayIndex < input.length(); bracketArrayIndex++) {
                    characterAt = input.charAt(bracketArrayIndex);
                    if ((characterAt == '{') || (characterAt == '(') || ((characterAt == '['))) {
                        stringStack.push(Character.toString(characterAt));
                    }
                    if ((characterAt == '}') || (characterAt == ')') || ((characterAt == ']'))) {
                        if (stringStack.empty()) {
                            isBracketedPair = false;
                            break;
                        } else {
                            char top = stringStack.pop().charAt(0);

                            if (((characterAt == '}') && (top == '{')) || ((characterAt == ']') && (top == '['))
                                    || ((characterAt == ')') && (top == '('))
                            ) {
                                isBracketedPair = true;
                            }
                        }
                    }
                }
            }
        }else System.out.println("Invalid Input");
        return isBracketedPair;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to bracket Balancer");
        System.out.println("Is the Bracket exist in pair and correct order : " + BracketBalancingUsingStack.bracketChecker());
    }
}