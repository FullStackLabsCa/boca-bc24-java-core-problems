package problems.collection.p1_Bracket_WS_Matching;

import java.util.Scanner;

public class P1_Bracket_WS_Matching {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // loop for keep asking till get the right input
        while (true) {

            System.out.println("Please enter the string of brackets OR 'Q' for exit: ");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("Q")){
                System.out.println("Thank you. By.");
                break;
            }

            if(!input.matches("[(){}\\[\\]]*") || !input.matches("")){  // refer: notes3-regular-expression.md
                System.out.println("Invalid Input");
                System.out.println("Please enter valid input brackets: (), [], {} ");
                continue;
            }

            boolean result = inputCheckBalanedString(input);
            System.out.println(input + " is a balanced string ? " + result);


        }

        scanner.close();
    }

    // note1s.md - undersatnding the structure
    private static boolean inputCheckBalanedString(String input) {

        int squareBracket = 0, roundBracket = 0, curlyBracket = 0;

        for (char ch : input.toCharArray()) {  // refer: notes2-diff-char.md

            switch (ch) {

                case '(':
                    roundBracket++;
                    break;
                case ')':
                    roundBracket--;
                    if (roundBracket < 0) {
                        return false;
                    }
                    break;
                case '[':
                    squareBracket++;
                    break;
                case ']':
                    squareBracket--;
                    if (squareBracket < 0) {
                        return false;
                    }
                    break;
                case '{':
                    curlyBracket++;
                    break;
                case '}':
                    curlyBracket--;
                    if (curlyBracket < 0) {
                        return false;
                    }
                    break;

                default:
                    System.out.println("Wrong input");
                    return false;
            }


        }

        return squareBracket == 0 && roundBracket == 0 && curlyBracket == 0;
    }


}
