package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
*  Examples:

Input: {[(])}, Output: true (valid due to the custom rule)
Input: {[()]}, Output: true (valid due to standard balancing)
Input: {[}, Output: false (invalid, not balanced)
Input: {[(])}(), Output: true (valid, combination of twisted and standard sequences)
Input: [(]), Output: false (invalid, sequence does not match any valid pattern)
* */


public class BracketWithStack {
    public static void main(String[] args) {
        String input = "{[({()})]}";
        boolean result = isResult(input);
        System.out.println(result);
    }

    private static boolean isResult(String input) {
        if((input.length()%2!=0)){
            return false;
        }else{
            boolean flag = false;
            Stack<Character> bracketsStack = new Stack<>();
            for(int currentIndex=0; currentIndex<input.length(); currentIndex++){
                if(currentIndex == 0 && input.charAt(currentIndex) == '{'){
                    flag = true;
                }
                char currentElement = input.charAt(currentIndex);
                switch (currentElement){
                    case '(','{', '[':
                        bracketsStack.push(currentElement);
                        break;
                    case ')':
                        if(bracketsStack.peek() == '('){
                            bracketsStack.pop();
                        }else if(flag && bracketsStack.peek() == ']'){
                            if(bracketsStack.contains('(')){
                                bracketsStack.pop();
                            }else {
                                return false;
                            }
                        }
                        break;
                    case ']':
                        if(flag && bracketsStack.peek() == '(' && bracketsStack.contains('[')){
                            bracketsStack.pop();
                            bracketsStack.pop();
                            currentIndex++;
                            break;
                        }
                        if(bracketsStack.peek() == '['){
                            bracketsStack.pop();
                        }
                        break;
                    case '}':
                        if(bracketsStack.peek()=='{'){
                            bracketsStack.pop();
                        }
                        break;
                }
            }
            return bracketsStack.isEmpty() ? true : false;
        }
    }
}
