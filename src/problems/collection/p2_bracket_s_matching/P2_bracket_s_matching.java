package problems.collection.p2_bracket_s_matching;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class P2_bracket_s_matching {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Custom Balanced Brackets Validator with Stack:");
            System.out.println("Please enter the string of brackets OR 'Q' for exit");

            String brackets = scanner.nextLine();
            if (brackets.matches("^[a-z][a-zA-Z0-9]*$") || brackets == null || brackets.isEmpty()) {
                System.out.println("Please enter valid brackets");
                continue;
            }
            if (brackets.charAt(0) == 'Q') {
                System.out.println("Thank you! \nBy.");
                break;
            }

            boolean result = inputCheckBalanedString(brackets);
            System.out.println(brackets + " is a balanced string ? " + result);

        }
    }

    private static boolean inputCheckBalanedString(String brackets) {
        HashMap<Character, Character> hashMapBrackets = new HashMap<>();
        hashMapBrackets.put('(', ')');
        hashMapBrackets.put('{', '}');
        hashMapBrackets.put('[', ']');
        hashMapBrackets.put('<', '>');

        Stack<Character> stackCharacters = new Stack<>();
        int counter = 0;

        for (int i = 0; i < brackets.length(); i++) {
            char currentChar = brackets.charAt(i);

            if (hashMapBrackets.containsKey(currentChar)) {
                stackCharacters.push(currentChar);
                counter++;
            } else if (hashMapBrackets.containsValue(currentChar)) {
                if (!stackCharacters.isEmpty() && hashMapBrackets.get(stackCharacters.peek()) == currentChar) {
                    stackCharacters.pop();
                    counter--;
                } else {
                    counter--;
                }
            }
        }

        if (counter==0){
            System.out.println("True");
            return true;
        }else {
            System.out.println("False");
            return false;
        }

    }


}