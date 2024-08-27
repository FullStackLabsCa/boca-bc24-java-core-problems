package collectionproblem;

import java.util.Scanner;
import java.util.Stack;

public class BracketMatchWithStack {
    public static void main(String[] args) {
        // create the scanner object to read the value
        Scanner scanner = new Scanner(System.in);

        //prompt the user to enter the expression
        System.out.println("Enter the expression with brackets");
        String expression = scanner.nextLine();

        //close the scanner
        scanner.close();

        //validate the expression and print the result
        if(matchingExpression(expression)){
            System.out.println("the expression is valid");
        }else{
            System.out.println("the expression is not valid");
        }
    }
    public static boolean matchingExpression(String expression){

        //create the stack to keep tack of brackets
        Stack<Character> stack = new Stack<>();
        //traverse for each expression

        for(char ch :expression.toCharArray()){

            //push the opening brackets into the stack
            if(ch =='(' || ch=='['||ch=='{'){
                stack.push(ch);
            }

            // for closing brackets
            else if (ch==')' || ch==']' || ch=='}'){
                //check if the stack is empty
                if(stack.isEmpty()){
                    return false;
                }
                char top=stack.pop();
                if(!isMatchingBracket(top,ch)){
                    return false;

                }
            }
        }
        //stack should be empty if all the brackets are properly closed
        return stack.isEmpty();
    }
    private static boolean isMatchingBracket(char openB, char closeB){
        return (openB=='(' && closeB==')')||
                (openB=='[' && closeB==']')||
                (openB=='{'&& closeB=='}');
    }

}
