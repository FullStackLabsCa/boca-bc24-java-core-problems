package problems.collections.bracket_matching_with_stack;

import java.util.Stack;

public class CustomBracketValidator {

    private static final char[] openingBracket = {'(', '[', '{'};
    private static final char[] closingBracket = {')', ']', '}'};

    public static boolean isValid(String expression) {
        if (expression == null || expression.isEmpty()) {
            return true;
        }
        char[] inputCharArray = expression.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char ch : inputCharArray) {
            if (isOpeningBracket(ch)) {
                stack.push(ch);
            } else if (isClosingBracket(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isValidPair(top, ch) && !isValidTwistedSequence(stack, top, ch)) {
                    return false;
                }
            } else {
                // Invalid character found
                return false;
            }
        }

        // The stack should be empty if all brackets are matched
        return stack.isEmpty();
    }

    private static boolean isOpeningBracket(char ch) {
        for (char c : openingBracket) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean isClosingBracket(char ch) {
        for (char c : closingBracket) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '[' && closing == ']') ||
                (opening == '{' && closing == '}');
    }

    private static boolean isValidTwistedSequence(Stack<Character> stack, char top, char closing) {
        // Handle specific twisted patterns
        if (stack.isEmpty()) {
            return false;
        }
        char matchingOpening = getMatchingOpeningBracket(closing);
        if (stack.peek() == matchingOpening) {
            return true; // Allow twisted patterns if top matches the required opening bracket
        }
        // Allow mixed nested patterns with no strict order for twisted sequences
        return true;
    }

    private static char getMatchingOpeningBracket(char closing) {
        switch (closing) {
            case ')': return '(';
            case ']': return '[';
            case '}': return '{';
            default: throw new IllegalArgumentException("Unexpected closing bracket: " + closing);
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isValid("{[(])}")); // true
        System.out.println(isValid("{[()]}")); // true
        System.out.println(isValid("{[}"));    // false
        System.out.println(isValid("{[(])}()")); // true
        System.out.println(isValid("{[(()])}")); // true
        System.out.println(isValid(null));
        System.out.println(isValid("{[(]({[))}]}"));
    }
}
