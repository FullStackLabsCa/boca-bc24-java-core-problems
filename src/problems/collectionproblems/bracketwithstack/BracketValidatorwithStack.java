package problems.collectionproblems.bracketwithstack;

import java.util.Scanner;
import java.util.Stack;

public class BracketValidatorwithStack {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String aString;
        do {
            System.out.println("Please enter expression: (<cr> to quit)");
            aString = keyboard.nextLine();
            if (bracketsMatchWithStack(aString))
                System.out.println("The brackets match");
            else
                System.out.println("The brackets do not match");
        } while (aString.length() > 0);
    }

    public static boolean bracketsMatchWithStack(String stringFromCommandLine) {
        Stack<Character> aStack = new Stack<>();
        char character;

        for (int i=0;i< stringFromCommandLine.length();i++) {
            character = stringFromCommandLine.charAt(i);

            if (character == '(' || character == '[' || character == '{') {
                aStack.push(character);
            } else if (character == ')' || character == ']' || character == '}') {
                if (aStack.isEmpty()) {
                    return false; // no open bracket for this closed one
                }
                Character top = aStack.peek();

                if ((character == ')' && top == '(') ||
                        (character == ']' && top == '[') ||
                        (character == '}' && top == '{')) {
                    aStack.pop(); // Correct match
                } else if ((character == ')' && top == '{') ||
                        (character == '}' && top == '[') ||
                        (character == ']' && top == '(')) {
                    aStack.pop(); // Valid "twisted" match
                } else {
                    return false; // No valid match
                }
            }
        }
        return aStack.isEmpty(); // No unmatched opening brackets left
    }
}