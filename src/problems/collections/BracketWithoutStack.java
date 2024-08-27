package problems.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BracketWithoutStack {

    private static List<Character> bracketList;

    public static void main(String[] args) {
        String brackets = "";
//         String brackets = "{[[]]}";
//        String brackets = "{[(])}";(
//         String brackets = "[]";
//         String brackets = "{[}";

        Scanner reader = new Scanner(System.in); // Reading from System.in
        System.out.println("Enter your Expression : ");
        brackets = reader.nextLine();
        reader.close();

        char[] parts = brackets.toCharArray();
        bracketList = new ArrayList<>();
        for (char c : parts) {
            bracketList.add(c);
        }
        System.out.println("isBalanced : " + verifyBrackets());

    }

    private static boolean verifyBrackets() {
        boolean isBalanced = false;
        int lastIndex = 0;
        if (bracketList.size() % 2 == 0) {
            for (int i = 0; i < bracketList.size(); i++) {
                lastIndex = bracketList.size() - 1;
                if (isMatchingPair(bracketList.get(i), bracketList.get(lastIndex))) {
                    bracketList.remove(lastIndex);
                    bracketList.remove(i);
                    i--;
                    isBalanced = true;
                } else {
                    isBalanced = false;
                    break;
                }
            }
        }
        return isBalanced;
    }

    private static boolean isMatchingPair(char open, char close) {
        boolean returnPair = false;
        if (open == '(' && close == ')') {
            System.out.println("Parantheses");
            returnPair = true;
        } else if (open == '[' && close == ']') {
            System.out.println("Square Brackets");
            returnPair = true;
        } else if (open == '{' && close == '}') {
            System.out.println("Curly Braces");
            returnPair = true;
        }
        return returnPair;
    }

  /*  private static boolean verifyBrackets1(String brackets) {
        boolean isBalanced = true;
        int curlyCounter = 0;
        int squareCounter = 0;
        int angularCounter = 0;

        if (brackets.length() % 2 == 0) {
            String[] parts = brackets.split("");
            for (int i = 0; i < brackets.length(); i++) {
                char ch = parts[i].charAt(0);
                System.out.println("Char : " + ch);
                switch (ch) {
                    case '{':
                        curlyCounter++;
                        break;
                    case '}':
                        curlyCounter--;
                        break;
                    case '(':
                        angularCounter++;
                        break;
                    case ')':
                        angularCounter--;
                        break;
                    case '[':
                        squareCounter++;
                        break;
                    case ']':
                        squareCounter--;
                        break;
                }
            }
            if (angularCounter == 0 && squareCounter == 0 && curlyCounter == 0) {
                isBalanced = true;
            } else {
                isBalanced = false;
            }
        } else {
            return false;
        }
        return isBalanced;
    }*/

}