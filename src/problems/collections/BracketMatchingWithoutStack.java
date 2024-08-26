package problems.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BracketMatchingWithoutStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().trim();
        if (str.length()%2==0){
            List<Character> brackets = new ArrayList<>();
            for(int i=0; i<str.length(); i++){
                if(str.charAt(i) == '[' || str.charAt(i) == '{' || str.charAt(i) == '('){
                    brackets.add(str.charAt(i));
                } else if((str.charAt(i) == ']' && brackets.get(brackets.size()-1) == '[')
                || (str.charAt(i) == '}' && brackets.get(brackets.size()-1) == '{')
                || (str.charAt(i) == ')' && brackets.get(brackets.size()-1) == '(')){
                    brackets.remove(brackets.size()-1);
                } else break;
            }

            System.out.println(brackets.isEmpty());
        } else System.out.println(false);
    }
}
