package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static Stack<Double> memoryStorage = new Stack<>();
        public static void main(String[] args) {
//            System.out.println(3 + 5 * 2 - 4 / 2);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an expression (e.g., 2+3-2*8): ");
            String input = scanner.nextLine();
            double result = calculate(input);
            System.out.println("Result: " + result);
        }
        public static double calculate(String input){

            int indexOfMemory = input.indexOf("M");
            if(indexOfMemory>0) {
                String memoryInput = input.substring(0,indexOfMemory);
                double result = evaluateExpression(memoryInput);
                storeInMemory(result);
                return result;
            }

            if(input.contains("^")){
                String[] operands = input.split("[+\\-*/^]");
                double a = Double.parseDouble(operands[0]);
                double b = Double.parseDouble(operands[1]);
                return Math.pow(a,b);
            } else if (input.contains("sqrt")) {
                int openBracketIndex = input.lastIndexOf('(');
                int closeBracketIndex = input.indexOf(")",openBracketIndex);
                String innerExpression = input.substring(openBracketIndex+1,closeBracketIndex);
                double number = Double.parseDouble(innerExpression);
                if(number<0){
                    throw new ArithmeticException();
                }else {
                    return Math.sqrt(number);
                }

            }
            if(!input.contains("(")){
                return evaluateExpression(input);
            }  else {
                int openBracketIndex = input.indexOf('(');
                int closeBracketIndex = input.indexOf(")",openBracketIndex);
                String innerExpression = input.substring(openBracketIndex+1,closeBracketIndex);
                double innerResult = evaluateExpression(innerExpression);
                String newEvaluatedExpression = input.substring
                        (0,openBracketIndex)+innerResult+ input.
                        substring(closeBracketIndex + 1);
                return evaluateExpression(newEvaluatedExpression);
            }
        }
        public static double evaluateExpression(String expression) {


            expression = expression.replaceAll(" ", "");
            List<Double> numbers = new ArrayList<>();
            List<Character> operators = new ArrayList<>();
            StringBuilder currentNumber = new StringBuilder();
            for (char ch : expression.toCharArray()) {
                if (Character.isDigit(ch) || ch == '.') {
                    currentNumber.append(ch);
                }
              else {
                    numbers.add(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                    operators.add(ch);
                }
            }
            if (!currentNumber.isEmpty()) {
                numbers.add(Double.parseDouble(currentNumber.toString()));
            }
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '*' || operators.get(i) == '/') {
                    double left = numbers.get(i);
                    double right = numbers.get(i + 1);
                    double result = 0;

                    if (operators.get(i) == '*') {
                        result = left * right;
                    } else if (operators.get(i) == '/') {
                        if(right==0){
                            result = Double.NaN;
                        }else {
                            result = left / right;
                        }
                    }
                    numbers.set(i, result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--;
                }
            }
            double finalResult = numbers.get(0);
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '+') {
                    finalResult += numbers.get(i + 1);
                } else if (operators.get(i) == '-') {
                    finalResult -= numbers.get(i + 1);
                }
            }

            return finalResult;
        }

    public static void clearMemory() {
            memoryStorage.clear();
    }
        public static void storeInMemory(double number){
            memoryStorage.push(number);

        }
    public static double recallMemory() {
        if (!memoryStorage.isEmpty()) {
            return memoryStorage.peek();
        } else {
            throw new IllegalStateException("No values stored in memory.");
        }
    }

    public static String recallAllMemory() {
        if (memoryStorage.isEmpty()) {
            return "No values stored in memory.";
        }
        StringBuilder sb = new StringBuilder();
        for(double value:memoryStorage){
            sb.append(value).append(", ");
        }
        sb.setLength(sb.length()-2);
        return "Stored values: "+sb.toString();
    }

}

