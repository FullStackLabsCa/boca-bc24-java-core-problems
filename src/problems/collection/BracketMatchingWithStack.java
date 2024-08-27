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

The program should return true if the input string is considered valid according to the custom rules, and false otherwise.*/

import java.util.Scanner;
import java.util.Stack;

public class BracketMatchingWithStack {

    public static Boolean bracketMatch(String input) {
        Boolean isMatched = false;
        if(input == null) return false;
        if(!(input.contains("[") || input.contains("(") || input.contains("{"))) return false;

        Stack<Character> characterStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) =='(' || input.charAt(i) =='{'||input.charAt(i) =='['){
                characterStack.push(input.charAt(i));
            }

            if(input.charAt(i) == ')' || input.charAt(i) == '}' || input.charAt(i) == ']'){
//            if(input.contains(")") || input.contains("}")||input.contains("]")){
                if(!characterStack.isEmpty()) {
                    int check = i;
                    while (check < input.length()) {
                        if (bracketMatch(characterStack.pop(), input.charAt(check))){
                            isMatched = true;
                        }else {
                            isMatched = false;
                        }
//                    if(!(input.charAt(i) == characterStack.pop())) {
                        check++;
                    }
                }
                }
            }
        return isMatched;
    }

    static Boolean bracketMatch(Character left, Character right) {
        return left == '(' && right == ')' || left == '{' && right == '}' || left == '[' && right == ']';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringBracketInput = scanner.nextLine();
        System.out.println("stringBracketMatched = " +bracketMatch(stringBracketInput));

    }

}
