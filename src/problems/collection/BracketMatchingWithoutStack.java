package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BracketMatchingWithoutStack {
    public static boolean isBracketMatching(String inputString) {
        List<Character> bracketList = new ArrayList<>();
        if (inputString.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < inputString.length(); i++) {
                if (inputString.charAt(i) == '[' || inputString.charAt(i) == '(' || inputString.charAt(i) == '{') {
                    bracketList.add(inputString.charAt(i));
                } else if ((inputString.charAt(i) == '}' && bracketList.get(bracketList.size() - 1) == '{') ||
                        (inputString.charAt(i) == ']' && bracketList.get(bracketList.size() - 1) == '[') ||
                        (inputString.charAt(i) == ')' && bracketList.get(bracketList.size() - 1) == '(')
                ) {
                    bracketList.remove(bracketList.size() - 1);
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
