package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BracketMatchingWithoutStack {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        System.out.print("Enter String => ");
        String str = scanner.nextLine().trim().replaceAll(" ","");

        System.out.println();
        System.out.println(bracketChaecker(str));
    }


    public static boolean bracketChaecker(String str){
        Map<Character, Character> bracketsMap = new HashMap<>();
        bracketsMap.put('{','}');
        bracketsMap.put('(',')');
        bracketsMap.put('[',']');
        int curlyCount = 0;
        int roundCount = 0;
        int squareCount = 0;

        for (char ch : str.toCharArray()) {
            if (bracketsMap.containsKey(ch)) {
                if (ch == '{') curlyCount++;
                else if (ch == '(') roundCount++;
                else if (ch == '[') squareCount++;
            } else if (bracketsMap.containsValue(ch)) {
                if (ch == '}') curlyCount--;
                else if (ch == ')') roundCount--;
                else if (ch == ']') squareCount--;

                if (curlyCount < 0 || roundCount < 0 || squareCount < 0) {
                    return false;
                }
            }
        }

        return curlyCount == 0 && roundCount == 0 && squareCount == 0;

    }
}
