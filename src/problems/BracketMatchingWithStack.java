package problems;

import java.util.*;

public class BracketMatchingWithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter Input String  (x/X to exit)= ");
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("x")) {
                return;
            }
            System.out.println("input = " + input);
            input = input.replaceAll("[^{(\\[})\\]]", "").trim();
            if (input.isEmpty()) {
                System.out.println("Input string can not be blank or should have at least one bracket , please enter valid value.");
            } else {
                System.out.println("Is brackets balanced = " + isBananced(input));
            }
        } while (true);
    }

    private static Boolean isBananced(String input) {
        Boolean result = true;
        input = input.replaceAll("[^{(\\[})\\]]", "").trim();
        if (input.isEmpty()) {
            System.out.println("Input string can not be blank or should have at least one bracket , please enter valid value.");
            return false;
        }
        Map<Character, Character> bracketsMap = new HashMap<>();
        bracketsMap.put('}', '{');
        bracketsMap.put(')', '(');
        bracketsMap.put(']', '[');
        Set<Character> keySet = bracketsMap.keySet();
        if (input.length() % 2 == 1) {
            return false;
        }
        List<Character> inputString2CharList = input.chars().mapToObj(c -> (char) c).toList();
        Stack<Character> resultStack = new Stack<>();

        for (Character ch : inputString2CharList) {
            if (keySet.contains(ch)) {
                if (resultStack.peek().equals(bracketsMap.get(ch))) {
                    resultStack.pop();
                } else {
                    return false;
                }
            } else {
                resultStack.add(ch);
            }
        }
        return result;
    }
}
