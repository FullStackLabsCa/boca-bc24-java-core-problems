package problems.collections;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class BracketMatchingWithStack {
    public static void main(String[] args) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter brackets: ");
            String brackets = input.nextLine();
            if (brackets == null || brackets.isEmpty()) {
                System.out.println("Entered string is null");
                break;
            }
            if (brackets.charAt(0) == 'x') {
                break;
            }

            HashMap<Character, Character> hashmapBrackets = new HashMap<>();
            hashmapBrackets.put('(', ')');
            hashmapBrackets.put('[', ']');
            hashmapBrackets.put('{', '}');
            hashmapBrackets.put('<', '>');

            Stack<Character> stack = new Stack<>();
            int ctr = 0;

            for (int i = 0; i < brackets.length(); i++) {
                char currentChar = brackets.charAt(i);

                if (hashmapBrackets.containsKey(currentChar)) {
                    stack.push(currentChar);
                    ctr++;
                } else if (hashmapBrackets.containsValue(currentChar)) {
                    if (!stack.isEmpty() && hashmapBrackets.get(stack.peek()) == currentChar) {
                        stack.pop();
                        ctr--;
                    } else {
                        ctr--;
                    }
                }
            }

            if (ctr == 0) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
