package collections;

import java.util.ArrayList;
import java.util.List;

public class BracketWithoutStack {
    public static void main(String[] args) {
        String input = "{}";
        boolean result = isResult(input);
        System.out.println(result);
    }

    private static boolean isResult(String input) {
        if ((input.length() % 2) != 0) {
            return false;
        }

        List<Character> brackets = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            switch (currentChar) {
                case '(':
                case '{':
                case '[':
                    brackets.add(currentChar);
                    break;

                case ')':
                    if (brackets.isEmpty() || brackets.get(brackets.size() - 1) != '(') {
                        return false;
                    }
                    brackets.remove(brackets.size() - 1);
                    break;

                case '}':
                    if (brackets.isEmpty() || brackets.get(brackets.size() - 1) != '{') {
                        return false;
                    }
                    brackets.remove(brackets.size() - 1);
                    break;

                case ']':
                    if (brackets.isEmpty() || brackets.get(brackets.size() - 1) != '[') {
                        return false;
                    }
                    brackets.remove(brackets.size() - 1);
                    break;

                default:
                    return false;
            }
        }

        return brackets.isEmpty();
    }
}
