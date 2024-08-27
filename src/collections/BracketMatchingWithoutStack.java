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
        if((input.length() % 2) != 0) {
            return false;
        }

        Map<Character, Character> bracketMapping = new HashMap<>();
        bracketMapping.put('(',')');
        bracketMapping.put('{','}');
        bracketMapping.put('[',']');

        boolean result = false;
        String openings = input.substring(0, input.length() / 2);
        String closings = input.substring((input.length() / 2));

        //Add Validation if opening contain any of the keys from the bracketsMapping then return false
        //Not sure if it is required right now. So Skipping.
        //What if we want to add " " , or ' ' , or / / to the map

        // Reverse String closings
        String reversedClosings = StringReversal.stringReveral(closings);

        //Check for the match of opening and closing string
        for (int i = 0; i < openings.length(); i++) {
            if(reversedClosings.charAt(i) == bracketMapping.get(openings.charAt(i))){
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

    }
}
