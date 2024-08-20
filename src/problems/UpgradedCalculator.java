package problems;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpgradedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an expression to evaluate: ");

        String expr = scanner.nextLine();
        String expression = expr.replaceAll("\\s", "");



        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find())
        {
            System.out.println(matcher);
            System.out.println(matcher.find());
//            System.out.println(matcher);
        }
    }

//    public static double solveBracket(String exp){
//
//    }

}