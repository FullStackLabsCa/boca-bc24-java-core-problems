package collections;

import problems.StringReversal;

import java.util.*;

public class BracketMatchingWithoutStack {

    public static boolean matchBrackets(String input) {

        //Validate Input
        if (input == null || input.trim().isEmpty() || input.matches(".*[a-zA-Z].*")) {
            return false;
        }

        //Otherwise
        if((input.trim().length() % 2) != 0) {
            return false;
        }

        Map<Character, Character> bracketMapping = new HashMap<>();
        bracketMapping.put('(',')');
        bracketMapping.put('{','}');
        bracketMapping.put('[',']');

        boolean result = false;

        //Add Validation if opening contain any of the keys from the bracketsMapping then return false
        //Not sure if it is required right now. So Skipping.
        //What if we want to add " " , or ' ' , or / / to the map

        //Check for the match of first and last character coming towards the middle
        for (int i = 0; i < (input.trim().length() / 2); i++) {
            if(input.trim().charAt(input.length() - i - 1) == bracketMapping.get(input.trim().charAt(i))){
                result = true;
            } else {
                return false;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(BracketMatchingWithoutStack.matchBrackets("{[()]}"));
        System.out.println(BracketMatchingWithoutStack.matchBrackets("{[(])}"));
        System.out.println(BracketMatchingWithoutStack.matchBrackets("[]"));
        System.out.println(BracketMatchingWithoutStack.matchBrackets("{[}"));
//        System.out.println("------");
//        System.out.println(BracketMatchingWithoutStack.matchBrackets(""));
//        System.out.println(BracketMatchingWithoutStack.matchBrackets(null));
//        System.out.println(BracketMatchingWithoutStack.matchBrackets("aabb"));

        //The code will FAIL for this "[()()]"

    }
}
