package problems.collections;

import java.util.Scanner;
import java.util.Stack;

@SuppressWarnings("java:S106")
public class BracketsValidatorWithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter brackets");
        String inputValue = scanner.nextLine().trim();

        if (checkParenthesis(inputValue))
            System.out.println("Match case");
        else
            System.out.println("Not Matched");
    }

    public static boolean checkParenthesis(String str) {
        Stack<Character> characters = new Stack<>();
        char[] charArray = str.toCharArray();
        for (char ch : charArray) {
            switch (ch) {
                case '{', '[', '(':
                    characters.push(ch);
                    break;

                case '}':
                    if (characters.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (characters.pop() != '[') {
                        return false;
                    }
                    break;
                case ')':
                    if (characters.pop() != '(') {
                        return false;
                    }
                    break;

                default:
                    return false;
            }
        }

        return characters.isEmpty();
    }
}
