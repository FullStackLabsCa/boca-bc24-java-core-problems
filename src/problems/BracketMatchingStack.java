package problems;

import java.util.Scanner;
import java.util.Stack;

public class BracketMatchingStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the brackets ");
        String input = scanner.nextLine();
        char[] checker;
        checker = input.toCharArray();
        if(checker[0]!='('&& checker[0]!='{'&& checker[0]!='['){
            System.out.println("Invalid Input or starting with closing bracket");
        }else{
            System.out.println(bracketStackMatch(input));

        }

    }
    public static boolean bracketStackMatch(String input) {
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }
}
