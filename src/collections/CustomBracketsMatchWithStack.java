package collections;

import java.util.Stack;

public class CustomBracketsMatchWithStack {

    //Using 2 Stacks
    public static boolean customBracketValidator(String input){
        boolean result = false;

        //Validate Input
        if (input == null || input.trim().isEmpty() || input.matches(".*[a-zA-Z].*")) {
            return false;
        }
        //If Odd number of elements
        if((input.trim().length() % 2) != 0) {
            return false;
        }

        char[] arrayOfInput = input.trim().toCharArray();
        Stack<Character> mainStack = new Stack<>();
        Stack<Character> tempStack = new Stack<>();

        for(char element : arrayOfInput){
            if(element == '(' || element == '{' || element == '['){
                //Add to Main Stack
                mainStack.push(element);
            } else {
                //Check will all the exisiting elements in the mainStack
                while(!mainStack.isEmpty()) {
                    // Pop Element out of Stack
                    char poppedElement = mainStack.pop();
                    if (((element == ')') && (poppedElement == '(')) ||
                        ((element == '}') && (poppedElement == '{')) ||
                        ((element == ']') && (poppedElement == '['))) {
                        //Match Found
                        //Move all the elements from the tempStack to the main stack
                        while(!tempStack.isEmpty()){
                            mainStack.push(tempStack.pop());
                        }
                        break;
                    } else {
                        tempStack.push(poppedElement);
                    }
                }
            }
        }

        if(tempStack.isEmpty() && mainStack.isEmpty()){
            result = true;
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(CustomBracketsMatchWithStack.customBracketValidator("{[(])}"));
        System.out.println(CustomBracketsMatchWithStack.customBracketValidator("{[()]}"));
        System.out.println(CustomBracketsMatchWithStack.customBracketValidator("{[}"));
        System.out.println(CustomBracketsMatchWithStack.customBracketValidator("{[(])}()"));
        System.out.println(CustomBracketsMatchWithStack.customBracketValidator("{(([))]}"));
        System.out.println(CustomBracketsMatchWithStack.customBracketValidator("{([{[(])})]}"));
        System.out.println(CustomBracketsMatchWithStack.customBracketValidator("{[(]]}"));
//        Input: {[(])}, Output: true (valid due to the custom rule)
//        Input: {[()]}, Output: true (valid due to standard balancing)
//        Input: {[}, Output: false (invalid, not balanced)
//        Input: {[(])}(), Output: true (valid, combination of twisted and standard sequences)
//        Input: [(]), Output: false (invalid, sequence does not match any valid pattern)
    }
}
