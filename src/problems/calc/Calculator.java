package problems.calc;

import java.util.*;

public class Calculator {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression: ");
        String input = scanner.nextLine().trim();

        double result = calculate(input);
        System.out.println("Result: " + result);
    }

//    public static double calculate(String expr){
//        if(!expr.contains("(")){
//            return evaluateExpression(expr);
//        }
//        else{
//            if()
//        }
//    }

    public static double evaluateExpression(String expr) {
        List<Character> operation = new ArrayList<>();
        List<Double> numbers = new ArrayList<>();

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/') {
                operation.add(expr.charAt(i));
                numbers.add(Double.parseDouble(number.toString()));
                number.setLength(0);
            } else {
                number.append(expr.charAt(i));
            }
        }

        if (!number.isEmpty()) {
            numbers.add(Double.parseDouble(number.toString()));
        }

        double sum = numbers.get(0);
        for (int j = 0; j < operation.size(); j++) {
            if (operation.get(j) == '+') {
                sum += numbers.get(j + 1);
            } else if (operation.get(j) == '-') {
                sum -= numbers.get(j + 1);
            } else if (operation.get(j) == '/') {
                sum /= numbers.get(j + 1);
            } else if (operation.get(j) == '*') {
                sum *= numbers.get(j + 1);
            }
        }
            return sum;
    }
}