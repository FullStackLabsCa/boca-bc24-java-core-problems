package problems;

import java.util.*;

public class BracketMatchingWithoutStack {
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
                System.out.println("Is brackets balanced = " + isBalanced(input));
            }
        } while (true);
    }

    private static Boolean isBalanced(String input) {
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
        if (input.length() % 2 == 1) {
            return false;
        }

        List<Character> inputCharList = new ArrayList<>(input.chars().mapToObj(c -> (char) c).toList());
        Set<Character> keySet = bracketsMap.keySet();

        for (int i = 0; i < inputCharList.size(); i++) {
            if (keySet.contains(inputCharList.get(i))) {
                if (!bracketsMap.get(inputCharList.get(i)).equals(inputCharList.get(i - 1))) {
                    return false;
                } else {
                    inputCharList.remove(i - 1);
                    inputCharList.remove(i - 1);
                    i--;
                }

            }
        }
        return result;
    }
}
