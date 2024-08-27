package collection_problems;

import java.util.Scanner;
import java.util.Stack;

public class BracketBalancingUsingStack {
    public static boolean bracketChecker(String brackerassigned) {
        Stack<String> stringStack = new Stack<>();
        char characterAt;
        boolean isBracketedPair = false;
        if (brackerassigned.length() % 2 == 0) {
//            if (brackerassigned.charAt(0)==brackerassigned.charAt(brackerassigned.length()-1)) {
            for (int bracketArrayIndex = 0; bracketArrayIndex < brackerassigned.length(); bracketArrayIndex++) {
                characterAt = brackerassigned.charAt(bracketArrayIndex);
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
//            }else isBracketedPair =false;
        } else isBracketedPair = false;
        return isBracketedPair;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to bracket Balancer");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Is the Bracket exist in pair and correct order : " + bracketChecker(input));
    }
}