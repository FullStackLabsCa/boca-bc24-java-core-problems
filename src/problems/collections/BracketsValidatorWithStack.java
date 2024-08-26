package problems.collections;

import java.util.Scanner;
import java.util.Stack;

@SuppressWarnings("java:S106")
public class BracketsValidatorWithStack {
    public static void main(String[] args) {
        Stack<String> brackets = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        String enterBracketString = "";

        System.out.println("Please enter {[()]} and press q to quit");

        while (true) {
            String inputValue = scanner.nextLine().trim();
            if (inputValue.contains("q")) {
                System.out.println("Exiting from the loop......");
                break;
            }
            brackets.push(inputValue);
            enterBracketString += inputValue;
        }

        if (!brackets.isEmpty()) {
            Stack<String> convertedStack = new Stack<>();
            String str;

            str = brackets.pop();
            System.out.println(str);
            if (str == "{" || str == "[" || str == "(") {
                convertedStack.push(str);
            } else if (str == "}" || str == "]" || str == ")") {
                convertedStack.push(str);
            }

            System.out.println(convertedStack);


//            if (enterBracketString.equals(afterDoingStackOperation)) {
//                System.out.println("Match");
//            } else {
//                System.out.println("Not Match");
//            }
        }
    }
}
