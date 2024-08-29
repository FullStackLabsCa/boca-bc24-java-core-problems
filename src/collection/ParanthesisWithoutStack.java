package collection;

import java.util.Scanner;

public class ParanthesisWithoutStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter an expression to match");
        String input = scanner.nextLine();
        if(paranthesis(input)==true)
        System.out.println("Paranthesis are matching"+ input);
        else
            System.out.println("Paranthesis are not  matching"+ input);
    }
    public static boolean paranthesis(String expression) {
        int curly_bracket = 0;
        int square_bracket = 0;
        int round_bracket = 0;
        for (char character : expression.toCharArray()) {
            switch (character) {
                case '{':
                    curly_bracket++;
                    break;
                case '}':
                    curly_bracket--;
                    if(curly_bracket<0);
                    break;
                case '(':
                    round_bracket++;
                    break;
                case ')':
                    round_bracket--;
                    if(round_bracket<0)
                    break;
                case '[':
                    square_bracket++;
                    break;
                case ']':
                    square_bracket--;
                    if(square_bracket<0)
                    break;
                default:
            }
        }
        return curly_bracket ==0 && round_bracket == 0 && square_bracket==0;
    }
}
