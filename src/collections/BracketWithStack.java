package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BracketWithStack {
    public static void main(String[] args) {
        String input = "[(])";
        boolean result = isResult(input);
        System.out.println(result);
    }

    private static boolean isResult(String input) {
        Stack<Character> bracketsStack = new Stack<>();
        for(int currentIndex=0; currentIndex<input.length(); currentIndex++){
            char currentElement = input.charAt(currentIndex);
            switch (currentElement){
                case '(','{', '[':
                    bracketsStack.push(currentElement);
                    break;
                case ')':
                    int curr = bracketsStack.indexOf('(');
                    System.out.println(curr);
                case ']':
                case '}':

            }
        }
        return true;
    }
}
