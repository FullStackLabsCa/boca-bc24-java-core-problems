package problems.collections.stack;

import java.util.Scanner;
import java.util.Stack;

import static java.lang.String.valueOf;

public class MatchBracket {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Enter a string to check all the brackets are matching or not: ");
        String str = scanner.nextLine().trim();

        System.out.println(checkBrackets(str));
    }

    public static boolean checkBrackets(String s){
        Stack<Character> stack= new Stack<>();

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                case '{':
                case '[':
                    stack.push(ch);
                    break;

                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                    break;

                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                    break;

                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                    break;

                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}