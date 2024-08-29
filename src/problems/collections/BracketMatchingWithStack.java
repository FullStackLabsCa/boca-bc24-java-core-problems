package problems.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BracketMatchingWithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().trim();
        String regex = "\\{\\[\\(]\\)}|\\{\\(\\[\\)]}";
        Stack<Character> brackets = new Stack<>();
        if (str.length() % 2 == 0) {
            str = str.replaceAll(regex, "");
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '[' || str.charAt(i) == '{' || str.charAt(i) == '(') {
                    brackets.push(str.charAt(i));
                } else if ((str.charAt(i) == ']' && brackets.peek() == '[')
                        || (str.charAt(i) == '}' && brackets.peek() == '{')
                        || (str.charAt(i) == ')' && brackets.peek() == '(')) {
                    brackets.pop();
                } else break;
            }
            System.out.println(brackets.isEmpty());
        } else System.out.println(false);
    }
}
