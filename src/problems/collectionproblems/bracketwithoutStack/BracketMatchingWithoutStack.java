package problems.collectionproblems.bracketwithoutStack;

import java.util.ArrayList;

public class BracketMatchingWithoutStack {
    boolean findBracketMatch(String expression) {
        ArrayList<String> bracketExpression = new ArrayList<>();
        bracketExpression.add(expression);

        for (int i = 0; i < bracketExpression.size(); i++) {
            bracketExpression.set(i, bracketExpression.get(i).replaceAll("\\s", "").trim());
        }
        System.out.println("List Size :- " + bracketExpression.size());
        if (bracketExpression.size() == 1) {
            String element = bracketExpression.get(0);
            System.out.println("elements are :- " + element.length());

            if (element.length() % 2 != 0) {
                System.out.println("Brackets are not matching");
            } else {
                int midpoint = element.length() / 2;

                String part1 = element.substring(0, midpoint);
                String part2 = element.substring(midpoint);
                StringBuilder reversed = new StringBuilder(part2);
                String reversedPart2 =reversed.reverse().toString();
                System.out.println("Part 1 - "+part1);
                System.out.println("Part 1 length : "+part1.length());
                System.out.println("Part 2 Reverse - "+reversedPart2);
                System.out.println("Part 2 length : "+reversedPart2.length());

                int counter = 0;
                for (int i = 0; i < part1.length(); i++) {
                    System.out.println("Brackets I "+part1.charAt(i));

                    for(int j=0;j<reversedPart2.length();j++){
                        System.out.println("Brackets J "+reversedPart2.charAt(j));

                        if(part1.charAt(i)=='{' && reversedPart2.charAt(j)=='}'
                                ||part1.charAt(i)=='[' && reversedPart2.charAt(j)==']'
                                ||part1.charAt(i)=='(' && reversedPart2.charAt(j)==')') {
                                 counter++;
                                 break;
                        }else{
//                            break;
                        }
                    }
                }
                  System.out.println("Counter = " +counter);
                if(counter==part1.length()){
                    System.out.println("Brackets are matching ");
                }else{
                    System.out.println("Brackets are not matching ");

                }
            }
        }
            System.out.println(bracketExpression);
        return true;
    }
}
