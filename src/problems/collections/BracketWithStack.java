package problems.collections;

import java.util.Scanner;
import java.util.Stack;

public class BracketWithStack {

    public static void main(String[] args) {
        String brackets = "";
//         brackets = "{[[]]}";
//         brackets = "{[(])}";
//         brackets = "{[())}";
           brackets = "{[(])}()";
//         brackets = "{[}";

//        Scanner reader = new Scanner(System.in); // Reading from System.in
//        System.out.println("Enter your Expression to validate : ");
//        brackets = reader.nextLine();
//        reader.close();


        System.out.println("isBalanced : " + isBalancedStack(brackets));

    }

    public static boolean isBalancedStack(String brackets) {
        Stack<Character> bracketStack = new Stack<>();
        boolean isBalanced = true;
        if (brackets.length() % 2 == 0) {
            for (int i = 0; i < brackets.length(); i++) {
                char currentChar = brackets.charAt(i);

                if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                    bracketStack.push(currentChar);
                } else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
                    if (bracketStack.isEmpty() || !isMatchingPair(bracketStack.pop(), currentChar)) {
                        isBalanced = false;
                    }
                }
            }
            if (bracketStack.isEmpty()) {
                isBalanced = true;
            }
        }
        return isBalanced;
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }


}