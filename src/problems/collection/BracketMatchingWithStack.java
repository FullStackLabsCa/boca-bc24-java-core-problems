package problems.collection;

/*Objective:

Design and implement a Java program that uses a stack to validate expressions containing various types of brackets. Unlike the standard balanced bracket problem, this problem allows certain "twisted" bracket sequences that would traditionally be considered unbalanced. Specifically, the sequences {[(])}, {[()]}, and other similar patterns should be considered valid.

Requirements:

Bracket Types:

The expression will contain three types of brackets: parentheses (), square brackets [], and curly braces {}.
Valid Sequences:

The standard rules for balanced brackets apply, where every opening bracket must have a corresponding closing bracket, and they must be properly nested.
        However, in this twist, certain "twisted" sequences like {[(])} and {[()]} are also considered valid.
Any expression that doesn't follow these patterns or is unbalanced (e.g., {[}, [(])) should be considered invalid.
Input:

The program should accept a string consisting of only these three types of brackets.
Output:

The program should return true if the input string is considered valid according to the custom rules, and false otherwise.

Input: {[(])}, Output: true (valid due to the custom rule)
Input: {[()]}, Output: true (valid due to standard balancing)
Input: {[}, Output: false (invalid, not balanced)
Input: {[(])}(), Output: true (valid, combination of twisted and standard sequences)
Input: [(]), Output: false (invalid, sequence does not match any valid pattern)



*/



import java.util.Scanner;
import java.util.Stack;

public class BracketMatchingWithStack {

    public static Boolean bracketMatch(String input) {
        boolean isMatched = false;
        if (input == null || input.length() % 2 != 0) return false;
        if (!(input.contains("[") || input.contains("(") || input.contains("{"))) return false;
        Stack<Character> characterStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == '{' || input.charAt(i) == '[') {
                characterStack.push(input.charAt(i));
            }

            if (input.charAt(i) == ')' || input.charAt(i) == '}' || input.charAt(i) == ']') {
//            if(input.contains(")") || input.contains("}")||input.contains("]")){
                if (!characterStack.isEmpty()) {
                    int check = i;
                    Character popElement = characterStack.pop();
                    while (check < input.length()) {
                        if (bracketMatch(popElement, input.charAt(check))) {
                            isMatched = true;
                            break;
                        } else {
                            isMatched = false;
                        }
                        check++;
                    }
                }
            }
        }
        return characterStack.isEmpty() && isMatched;
    }

    static Boolean bracketMatch(Character left, Character right) {
        return left == '(' && right == ')' || left == '{' && right == '}' || left == '[' && right == ']';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringBracketInput = scanner.nextLine();
        System.out.println("stringBracketMatched = " + bracketMatch(stringBracketInput));

    }

}
