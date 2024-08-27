package problems;

import java.util.HashMap;
import java.util.Scanner;

public class BracketMatchingWithStack {
    public static void main(String[] args) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter brackets: ");
            StringBuilder brackets = new StringBuilder(input.nextLine());
            if ( brackets == null || brackets.isEmpty() ){
                System.out.println("Entered string is null " );
                break;
            }
            if (brackets.charAt(0) == 'x') {
                break;
            }

            HashMap<Character, Character> hashmapBrackets = new HashMap<>();
            hashmapBrackets.put('(', ')');
            hashmapBrackets.put('[', ']');
            hashmapBrackets.put('{', '}');
            int ctr = 0;
            while (brackets.length() > 0 && ctr == 0) {
                int j = 0;
//                System.out.println("start "+(brackets.charAt(j)));
                char requiredOutput = hashmapBrackets.get(brackets.charAt(j));
                for (int i = 1; i < brackets.length(); i++) {

//                    System.out.println("requiredOutput" + requiredOutput + "==" + brackets.charAt(i));

                    if (brackets.charAt(i) == (requiredOutput)) {
//                        System.out.println("requiredOutput+\"==\"+brackets.charAt(j) = " + requiredOutput + "==" + brackets.charAt(j));
                        brackets.deleteCharAt(i);
                        ctr++;
                        break;
                    }


                }
                brackets.deleteCharAt(j);
                ctr--;
//                System.out.println(brackets +"brackets.length()  = " + brackets.length() );


            }
            if(ctr<0) {
                System.out.println("False");
            }
            else {
                System.out.println("True");
            }
        }

    }
}
