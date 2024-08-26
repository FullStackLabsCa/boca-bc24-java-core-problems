package problems.arraysandstrings;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class UpgradedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sqrtRegex = "sqrt[(].+[)]";
        String numRegexWithNegative = "(-?\\d+.\\d+)|(\\d+)";
        String numRegexWithoutNegative = "(\\d+.\\d+)|(\\d+)";
        System.out.println("Enter an expression to evaluate: ");

        String expr = scanner.nextLine().trim();
        String expression = expr.replaceAll("\\s", "");
        System.out.println(expression.matches(""));
        while(expression.contains("sqrt")) {
            String num = StringUtils.substringBetween(expression, "sqrt(", ")");
            try{
                double number = Double.parseDouble(num);
                if(number < 0){
                    System.out.println("Square root of a negative number is not allowed.");
                    break;
                } else{
                    expression = expression.replaceFirst(sqrtRegex, Double.toString(Math.sqrt(number)));
                    System.out.println(expression);
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid Expression");
                break;
            }
        }

        List<String> list = new ArrayList<>(Arrays.stream(expression.split("")).toList());
        if(expression.matches("[/+\\-*^0-9.()]+")) {
            while (list.contains("(")) {
                int closingBracket = list.indexOf(")");
                list.remove(closingBracket);
                int j = 1;
                StringBuilder secondNumber = new StringBuilder();
                while (list.get(closingBracket - j).matches("(\\d+.?\\d+)|(\\d+)")) {
                    secondNumber.insert(0, list.get(closingBracket - j));
                    list.remove(closingBracket - j);
                    j++;
                }
                String operator = list.get(closingBracket - j);
                list.remove(closingBracket - j);
                j++;
                StringBuilder firstNumber = new StringBuilder();
                while (list.get(closingBracket - j).matches("(\\d+.?\\d+)|(\\d+)")) {
                    firstNumber.insert(0, list.get(closingBracket - j));
                    list.remove(closingBracket - j);
                    j++;
                }

                System.out.println(firstNumber);

                System.out.println(secondNumber);
                System.out.println(operator);

                double result = solveExpression(Double.valueOf(firstNumber.toString()), operator, Double.valueOf(secondNumber.toString()));
                if (result == Double.MIN_VALUE) System.out.print("Division by zero is not allowed.");
                else if(result == Double.MAX_VALUE) System.out.println("Invalid Expression.");
                else{
                    if(list.get(closingBracket-j).matches("\\("))
                    list.set(closingBracket-j, Double.toString(result));
//                    else list.
                }

                System.out.println(expression);
            }

        } else {
            System.out.println("Invalid Expression.");
        }

        System.out.println(list);

//        Stack<String> calcStack = new Stack<>();
//        String[] expArr = expression.split("");
//
//        int i=0;
//
//        while(i<expArr.length){
//            if(expArr[i].equals("(")){
//                calcStack.push("(");
//            } else if(expArr[i].matches("")){
//                String top = calcStack.pop();
//            }
//        }
    }
//    int i = 0;
//        for(String exp : expArr){
//            if(exp.equals("(")) calcStack.push(exp);
//            if(exp.matches("-?\\d")) {
//                if(calcStack.peek().matches("[+-/*^]")){
//                    exp
//                }
//                calcStack.push(exp);
//            }
//            if(exp.matches("[+-/*^]")) calcStack.push(exp);
//        }
//    }
//
    public static double solveExpression(Double first, String operator, Double second){

        return switch (operator) {
            case "/" -> {
                if (second == 0) {
//                    System.out.print("Division by zero is not allowed.");
                    yield Double.MIN_VALUE;
                }
                yield first / second;
//                    System.out.print("Division by zero is not allowed.");
            }
            case "*" -> first * second;
            case "+" -> first + second;
            case "-" -> first - second;
            default -> Double.MAX_VALUE;
        };
    }

//    public static String checkNumberParentheses(String expr){
//        if(expr.matches("[(]\\d+.\\d[)]|\\d")){
////            expr = expr.replaceAll();
//        }
//    }
}