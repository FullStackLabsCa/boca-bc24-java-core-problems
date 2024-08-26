package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BracketMatchingWithStack {
    public static boolean isBracketMatching(String inputString) {
        Stack<Character> bracketList = new Stack<>();

        if (inputString.isEmpty()) {
            return false;
        } else {
            inputString = inputString.replaceAll("\\{\\[\\(]\\)}", "");
            System.out.println("String is==" + inputString);
            for (int i = 0; i < inputString.length(); i++) {
                if (inputString.charAt(i) == '[' || inputString.charAt(i) == '(' || inputString.charAt(i) == '{') {
                    bracketList.push(inputString.charAt(i));
                } else if ((inputString.charAt(i) == '}' && bracketList.peek() == '{') ||
                        (inputString.charAt(i) == ']' && bracketList.peek() == '[') ||
                        (inputString.charAt(i) == ')' && bracketList.peek() == '(')
                ) {
                    bracketList.pop();
                }
            }
        }
        return bracketList.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an input string:: ");
        String str = scanner.nextLine().trim();
        System.out.println("Is brackets matching in a given input string = " + isBracketMatching(str));
    }
}
