package problems.collections.bracket_matching_without_stack;

import java.util.*;

public class BracketMatchingWithoutStack {

    public static void bracketChecker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input (brackets): ");
        String inputString = scanner.nextLine();

        // Check if the brackets are balanced using Map
        boolean balanced = isBalancedWithMap(inputString);

        if (balanced) {
            System.out.println("Balanced");
        } else {
            System.out.println("Not Balanced");
        }
    }

    public static boolean isBalancedWithMap(String input) {
        // Map to store matching pairs of brackets
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put('(', ')');
        bracketPairs.put('[', ']');
        bracketPairs.put('{', '}');

        // List to act as a stack substitute to track open brackets
        List<Character> openBrackets = new ArrayList<>();

        for (char c : input.toCharArray()) {
            if (bracketPairs.containsKey(c)) {
                // If it's an opening bracket, add to the list
                openBrackets.add(c);
            } else if (bracketPairs.containsValue(c)) {
                // If it's a closing bracket, check if it matches the last opened bracket
                if (openBrackets.isEmpty() || bracketPairs.get(openBrackets.remove(openBrackets.size() - 1)) != c) {
                    return false; // Not balanced
                }
            }
        }

        // After processing all characters, the list should be empty if balanced
        return openBrackets.isEmpty();
    }

    public static void main(String[] args) {
        // verify ()){}{[] , [{()}]
        BracketMatchingWithoutStack.bracketChecker();
    }
}
