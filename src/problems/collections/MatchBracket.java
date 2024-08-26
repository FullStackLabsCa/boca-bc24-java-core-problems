package problems.collections;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class MatchBracket {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Enter a string to check all the brackets are matching or not: ");
        String str = scanner.nextLine().trim();

        System.out.println(checkBrackets(str));
    }
    public static boolean checkBrackets(String s){
        int roundOpen = 0;
        int curlyOpen = 0;
        int squareOpen = 0;

        if(s.isEmpty()){
            System.out.println("Empty string not allowed.");
            return false;
        }
        else {
            char[] ch = s.toCharArray();
            for (char c : ch) {
                if (c == '(') {
                    roundOpen++;
                } else if (c == ')') {
                    if (roundOpen == 0) {
                        return false;
                    } else {
                        roundOpen--;
                    }
                } else if (c == '{') {
                    curlyOpen++;
                } else if (c == '}') {
                    if (curlyOpen == 0) {
                        return false;
                    } else {
                        curlyOpen--;
                    }
                } else if (c == '[') {
                    squareOpen++;
                } else if (c == ']') {
                    if (squareOpen == 0) {
                        return false;
                    } else {
                        squareOpen--;
                    }
                } else {
                    break;
                }
            }
        }
        return roundOpen==0 && squareOpen==0 && curlyOpen==0;
    }
}