package problems.collection;


import java.util.Scanner;


/*
Input: {[()]}, Output: true (balanced)
Input: {[(])}, Output: false (not balanced)
Input: [], Output: true (balanced)
Input: {[}, Output: false (not balanced)*/

public class BracketMatching {

    public static boolean isBracketMatch(String input) {
        if(input == null) return false;

        int leftIndex = input.length() / 2 - 1;
        int rightIndex = input.length() / 2;
        while (leftIndex >= 0) {
            if (!(bracketMatch(input.charAt(leftIndex), input.charAt(rightIndex)))) {
                return false;
            }
            leftIndex--;
            rightIndex++;
        }
        return true;
    }


    static Boolean bracketMatch(Character left, Character right) {
        return left == '(' && right == ')' || left == '{' && right == '}' || left == '[' && right == ']';
    }


    public static void main(String[] args) {

        System.out.println(" Enter the Brackets to match");
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine();
        System.out.println("isBracketMatch(stringInput) = " + isBracketMatch(stringInput));

    }
}
