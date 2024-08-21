package string_problem;

import java.util.Scanner;

public class Capitalize {
    public static String capitalizeWords(String input) {
        String firstCharUpper = null;
        String word="";
        String afterCapitalize = null;
        String[] splitStringArray = input.split(" ");
        for (String capitalLetter : splitStringArray) {
            char charAtFirst = capitalLetter.toUpperCase().charAt(0);
            firstCharUpper = capitalLetter.replace(capitalLetter.charAt(0), charAtFirst);
            afterCapitalize = word.concat(" ").concat(firstCharUpper);
            word = afterCapitalize.trim();
        }
        return afterCapitalize;
    }

    public static void main(String[] args) {
        System.out.println("string_problem.Capitalize the first letter of a Word in provided String");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("String string_problem.Capitalize first letter : ");
        System.out.print(capitalizeWords(input));
    }
}
