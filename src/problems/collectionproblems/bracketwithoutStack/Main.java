package problems.collectionproblems.bracketwithoutStack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your expression: Eg :- [({ })] ==> ");
        String expression = input.nextLine();
        BracketMatchingWithoutStack bracketmatching = new BracketMatchingWithoutStack();
        bracketmatching.findBracketMatch(expression);
        //        bracketmatching.findBracketMatch("{([ ] ) }");

    }
}
