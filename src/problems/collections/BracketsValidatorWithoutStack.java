package problems.collections;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class BracketsValidatorWithoutStack {
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
        if (str == null) return false;
        int leftIndex = (str.length() / 2) - 1;
        int rightIndex = (str.length() / 2);
        while (leftIndex >= 0) {
            if (!bracketMatch(str.charAt(leftIndex), str.charAt(rightIndex))) {
                return false;
            }
            leftIndex--;
            rightIndex++;
        }
        return true;
    }

    public static boolean bracketMatch(char leftBracket, char rightBracket) {
        return leftBracket == '{' && rightBracket == '}' || leftBracket == '[' && rightBracket == ']' || leftBracket == '(' && rightBracket == ')';
    }
}
