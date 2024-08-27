package problems;

import java.util.Scanner;

public class BracketMatching {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the brackets ");
        String input = scanner.nextLine();
        char[] checker;
        checker = input.toCharArray();
        if(checker[0]!='('&& checker[0]!='{'&& checker[0]!='['){
            System.out.println("Invalid Input or starting with closing bracket");
        }else{
            System.out.println(bracketMatch(input));

        }
    }

    public static boolean bracketMatch(String input){
        int roundCount = 0;
        int squareCount = 0;
        int curlyCount = 0;


        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                roundCount++;
            } else if (c == ')') {
                if (roundCount <= 0 || squareCount > 0 || curlyCount > 0) {
                    return false;
                }
                roundCount--;
            } else if (c == '[') {
                squareCount++;
            } else if (c == ']') {
                if (squareCount <= 0 || roundCount > 0 || curlyCount > 0) {
                    return false;
                }
                squareCount--;
            } else if (c == '{') {
                curlyCount++;
            } else if (c == '}') {
                if (curlyCount <= 0 || roundCount > 0 || squareCount > 0) {
                    return false;
                }
                curlyCount--;
            }
        }
        return roundCount == 0 && squareCount == 0 && curlyCount == 0;

    }
}
