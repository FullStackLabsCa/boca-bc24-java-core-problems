package collection;

import java.util.Scanner;
import java.util.Stack;

public class ParanthesisWithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter parantheis");
        String input = scanner.nextLine();
        if (checkParanthesis(input)) {
            System.out.println("paranthesis are balanced :" + input);
        } else
            System.out.println("Paranthesis are  not balanced :" + input);
    }
    public static boolean checkParanthesis(String input) {
        Stack<Character> characters = new Stack<>();
        char[] character = input.toCharArray();
        for (char c : character) {
            switch (c) {
                case '{':
                case '(':
                case '[':
                    characters.push(c);
                    break;
                case ')':
                    if (characters.pop() != '(') {
                        return false;
                    }
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
            }
        }
        return characters.isEmpty();
    }
}
