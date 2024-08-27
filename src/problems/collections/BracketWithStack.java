package problems.collections;

import java.util.Stack;

public class BracketWithStack {

    public static void main(String[] args) {
//        String brackets = "";
//         String brackets = "{[[]]}";
//        String brackets = "{[(])}";
//        String brackets = "{[())}";
         String brackets = "{[(])}()";
//         String brackets = "{[}";

//        Scanner reader = new Scanner(System.in); // Reading from System.in
//        System.out.println("Enter your Expression : ");
//        brackets = reader.nextLine();
//        reader.close();

        Stack<Character> stack = new Stack<>();
        for (char ch : brackets.toCharArray()) {
            stack.push(ch);
        }
//        System.out.println("isBalanced : " + verifyBrackets1(stack));
        System.out.println("isBalanced : " + verifyBrackets(brackets));

    }

    private static boolean verifyBrackets1(Stack<Character> brackets) {
        boolean isBalanced = true;
        int curlyCounter = 0;
        int squareCounter = 0;
        int paranthesesCounter = 0;

        if (brackets.size() % 2 == 0) {

            for (int i = 0; i < brackets.size(); i++) {
                char ch = brackets.get(i);
//                System.out.println("Char : " + ch);
                switch (ch) {
                    case '{':
                        curlyCounter++;
                        break;
                    case '}':
                        curlyCounter--;
                        break;
                    case '(':
                        paranthesesCounter++;
                        break;
                    case ')':
                        paranthesesCounter--;
                        break;
                    case '[':
                        squareCounter++;
                        break;
                    case ']':
                        squareCounter--;
                        break;
                    default:

                }
            }
            if (paranthesesCounter == 0 && squareCounter == 0 && curlyCounter == 0) {
                isBalanced = true;
            } else {
                isBalanced = false;
            }
        } else {
            return false;
        }
        return isBalanced;
    }


    private static boolean verifyBrackets(String input) {
        Stack<Character> stack = new Stack<>();
        char[] openBrackets = {'{', '[', '('};
        char[] closeBrackets = {'}', ']', ')'};

        for (char ch : input.toCharArray()) {
            if (isOpeningBracket(ch, openBrackets)) {
                stack.push(ch);
            } else if (isClosingBracket(ch, closeBrackets)) {
                if (stack.isEmpty() || !matches(stack.peek(), ch, openBrackets, closeBrackets)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static boolean isOpeningBracket(char ch, char[] openBrackets) {
        for (char open : openBrackets) {
            if (ch == open) {
                return true;
            }
        }
        return false;
    }

    private static boolean isClosingBracket(char ch, char[] closeBrackets) {
        for (char close : closeBrackets) {
            if (ch == close) {
                return true;
            }
        }
        return false;
    }

    private static boolean matches(char open, char close, char[] openBrackets, char[] closeBrackets) {
        for (int i = 0; i < openBrackets.length; i++) {
            if (open == openBrackets[i] && close == closeBrackets[i]) {
                return true;
            }
        }
        return false;
    }

}