package collections;

import java.util.*;

public class BracketMatchingWithoutStack {

    public static boolean matchBrackets(String input) {

        //Validate Input
        if (input != null && !input.trim().isEmpty()) {

            if (!input.matches(".*[a-zA-Z].*")) {

                Map<Character, Character> existingPairs = new HashMap<>();
                existingPairs.put('(', ')');
                existingPairs.put('{', '}');
                existingPairs.put('[', ']');

                char latest = input.trim().charAt(0);

                char[] charArray = input.trim().toCharArray();
                Set<Character> starts = existingPairs.keySet();

                for (char charUnderProcessing : charArray) {
                    if (starts.contains(charUnderProcessing)) {
                        latest = charUnderProcessing;
                    } else {
                        if (existingPairs.get(latest) == charUnderProcessing) {
                            return true;
                        } else return false;
                    }
                }
            }
        } else {
            return false;
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(BracketMatchingWithoutStack.matchBrackets("{[()]}"));
        System.out.println(BracketMatchingWithoutStack.matchBrackets("{[(])}"));
        System.out.println(BracketMatchingWithoutStack.matchBrackets("[]"));
        System.out.println(BracketMatchingWithoutStack.matchBrackets("{[}"));
        System.out.println(BracketMatchingWithoutStack.matchBrackets(""));
        System.out.println(BracketMatchingWithoutStack.matchBrackets(null));
        System.out.println(BracketMatchingWithoutStack.matchBrackets("aabb"));

    }
}
