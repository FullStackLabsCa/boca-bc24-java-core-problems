package StringProblems;

import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression to calculate :");
        String input = scanner.nextLine();
        System.out.println(calcualte(input));
    }
    public static String calcualte(String input) {
       // String[] part = input.split("(?<=[-+/*])|(?=[-+/*])");
        String[] part = input.split("\\\\d+|[-+*/]");
        double operator1 = Double.parseDouble(part[0]);
        double operator2 = Double.parseDouble(part[2]);
        String operand = part[1];
        switch (operand) {
            case "+":
                double add = operator1 + operator2;
                return "Addition of numbers are :"+ String.valueOf(add);
            case "-":
                double subtraction = operator1 - operator2;
                return "Subtraction of numbers are :"+String.valueOf(subtraction);
            case "*":
                double multiply = operator1 / operator2;
                return "Multiplication  of numbers are: " + String.valueOf(multiply);
            case "/":
                if(operator2==0){
                    return "second operator can't be zero";
                }
                double divison = operator1 / operator2;
                return "Divison of numbers are :"+String.valueOf(divison);
            default:
              return "invalid input";
        }

    }
}
