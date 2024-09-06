package problems;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private static final String ERROR_MSG = "Error: Invalid number format";

    static List<Double> calculatorMemory = new ArrayList<>();
    static int memorySize = 0;
    static final int MAX_MEMORY = 5;
    static boolean memoryOperation = false;

    public static void main(String[] args) {

//        String input = "8 + 5 M+";
//        String input = "(10 + 2) * 3";
//        String input = "3 + 5 * ( 9 / 3 ) / 2";
          String input = "10 / 0";
//        String input = "3 + (2 * 4) - ( 4 / 2 + 3) + 5 ";
//        String input = "sqrt(25) + 3 ^ 2";
//        String input = "sqrt(-9)";
//        String input = "sqrt(16)","2 ^ 3";
//        String input = "8 + 5";


        try {
            if (input.contains("M+")) {
                String pattern = "M\\+";
                input = input.replaceAll(pattern, "").trim();
                memoryOperation = true;
            }
            System.out.println(calculate(input));

            recallMemory();
            recallAllMemory();
            clearMemory();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Double calculate(String input) {

        if (input.contains("M+")) {
            String pattern = "M\\+";
            input = input.replaceAll(pattern, "").trim();
            memoryOperation = true;
        }

        Double output = 0.0;
        if (input == null || input.equalsIgnoreCase("") || input.trim().isEmpty()) {
//            return "Error: Input is empty or null";
        } else if (checkExpression(input) != 0) {
            switch (checkExpression(input)) {
                case 1:
                    if (checkExpression(input) == 0) {
                        String[] parts = input.split("\\s*[+\\-*/]\\s*");
                        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
//                            return "Error: Invalid input format";
                        } else {
//                            return ERROR_MSG;
                        }
                    }
                    output = (basicOperation(input));
                case 2:
                    output = Double.parseDouble(multiOperation(input));
                    break;
                case 3:
                    output = Double.parseDouble(paranthesisOperation(input));
                    break;
                case 4:
                    output = Double.parseDouble(AdvancedOperation(input));
                    break;
                case 5:
//                    output = memoryFunction(input).toString();
//                    break;
            }
        }

        if(memoryOperation){
            calculatorMemory.add(output);
        }
        if(output == 0.001){
            return Double.NaN;
        }else {
            return output;
        }
    }

    private static Double basicOperation(String input) {
        String returnString = "";
        String[] parts = input.trim().split("\\s*[+\\-*/]\\s*");
        float addendum1 = Integer.parseInt(parts[0]);
        float addendum2 = Integer.parseInt(parts[1]);
        String operator = input.replaceAll("[^+\\-*/]", "").trim();
        switch (operator) {
            case "/":
                if (addendum2 == 0) {
//                    return "Error: Cannot divide by zero";
                    return Double.NaN;
                } else {
                    returnString = "" + (addendum1 / addendum2);
                }
                break;
            case "*":
                returnString = "" + (addendum1 * addendum2);
                break;
            case "+":
                returnString = "" + (addendum1 + addendum2);
                break;
            case "-":
                returnString = "" + (addendum1 - addendum2);
                break;
            default:
                returnString = ERROR_MSG;
        }
        return  Double.parseDouble(returnString);
    }


    public static void storeInMemory(Double input) {
        if (memorySize > MAX_MEMORY) {
            calculatorMemory.set(0, input);
        } else {
            calculatorMemory.add(input);
            memorySize++;
        }
    }

    public static void clearMemory() {
        calculatorMemory = new ArrayList<>();
    }

    public static Double recallMemory() {
        if(!calculatorMemory.isEmpty()) {
            return calculatorMemory.get(calculatorMemory.size() - 1);
        }
    return null;
    }

    public static String recallAllMemory() {
        StringBuilder output = new StringBuilder("Stored values: ");
        if (calculatorMemory.isEmpty()) {
            return "No values stored in memory.";
        } else {
            for (Double value : calculatorMemory) {
                if (calculatorMemory.indexOf(value) == calculatorMemory.size() - 1) {
                    output.append(value);
                } else {
                    output.append(value).append(", ");
                }
            }
        }

        return output.toString();
    }

    private static String AdvancedOperation(String input) {

        input = input.replaceAll(" ", "");
        while (input.contains("sqrt")) {
            int start = input.indexOf("sqrt");
            int end = input.indexOf(')', start);
            String numberStr = input.substring(start + 5, end);
            int number = Integer.parseInt(numberStr);
            int result = (int) Math.sqrt(number);
            if(number<0) {
                throw new ArithmeticException("Invalid expression");
            }
            input = input.substring(0, start) + result + input.substring(end + 1);
        }

        return (multiOperation(input));
    }

    private static String paranthesisOperation(String input) {

        System.out.println("Parenthesis Operation ...");

        input = input.replaceAll(" ", "");
        StringBuilder tempString = new StringBuilder();

        if(input.contains("sqrt") || input.contains("^") ) {
            input = input.replaceAll(" ", "");
            while (input.contains("sqrt")) {
                int start = input.indexOf("sqrt");
                int end = input.indexOf(')', start);
                String numberStr = input.substring(start + 5, end);
                int number = Integer.parseInt(numberStr);
                int result = (int) Math.sqrt(number);

                if(number<0) {
                    throw new ArithmeticException("Invalid expression");
                }
                input = input.substring(0, start) + result + input.substring(end + 1);
            }
            return (multiOperation(input));
        }else {

            for (int lastIndex = 0; lastIndex < input.trim().length(); lastIndex++) {
                int startIndex = lastIndex;
                if (input.charAt(lastIndex) == '(') {
                    while (input.charAt(lastIndex) != ')') {
                        tempString.append(input.charAt(lastIndex + 1));
                        lastIndex++;
                    }
                    tempString = tempString.deleteCharAt(tempString.length() - 1);
                    String operationVal = (multiOperation(tempString.toString()));
                    input = input.replace(input.substring(startIndex, lastIndex + 1), String.valueOf(operationVal));

                }
            }
            return (multiOperation(input.toString()));
        }

    }

    private static String multiOperation(String input) {
        System.out.println("Multi Operation start ...." + input);

        List<Character> operators = new ArrayList<>();
        List<Double> numbers = new ArrayList<>();

        StringBuilder currentNumber = new StringBuilder();

//        for (Character character : input.toCharArray()) {
        for (int i=0; i< input.length(); i++) {
            char character  = input.charAt(i);
//            System.out.println(""+character);
            if (character != ' ') {
                if (Character.isDigit(character)) {
                    currentNumber.append(character);
                } else {
                    if (Character.isDigit(character) || character == '.') {
                        currentNumber.append(character);
                    }else{
                    if (currentNumber.length() > 0) {
                        numbers.add(Double.parseDouble(currentNumber.toString()));
//                        System.out.println("current number : "+currentNumber);
                        currentNumber.setLength(0);
                    }
                    }
//                    System.out.println("current number : "+currentNumber);
                    if (character == '+' || character == '-' || character == '*' || character == '/' || character == '^') {
                        operators.add(character);
                    }
//                    System.out.println("OP :"+operators);
                }
            }
        }
        if (currentNumber.length() > 0) {
            numbers.add(Double.parseDouble(currentNumber.toString()));
        }

        if (!input.isEmpty()) {

            int power = 0;
            while (power < operators.size()) {
                if (operators.get(power) == '^') {
                    double base = numbers.get(power);
                    double exponent = numbers.get(power + 1);
                    numbers.set(power,  Math.pow(base, exponent));
                    numbers.remove(power + 1);
                    operators.remove(power);
                } else {
                    power++;
                }
            }
            System.out.println("Numbers start :> " + numbers);
            System.out.println("Operators start :> " + operators);
            int i = 0;
            while (i < operators.size()) {

                if (operators.get(i) == '/') {
                    if (numbers.get(i + 1) == 0) {
                        return "0.001";
//                        System.out.println("Error: Cannot divide by zero");
                    } else {
                        double result = numbers.get(i) / numbers.get(i + 1);
                        numbers.set(i, result);
                        numbers.remove(i + 1);
                        operators.remove(i);
                    }

                } else if (operators.get(i) == '*') {
                    double result = numbers.get(i) * numbers.get(i + 1);
                    numbers.set(i, result);
                    numbers.remove(i + 1);
                    operators.remove(i);

                } else {
                    i++;
                }

            }

//            System.out.println("Numbers :> " + numbers.toString());
//            System.out.println("Operators :> " + operators.toString());
            i = 0;
            while (i < operators.size()) {
                String operator = String.valueOf(operators.get(i));
                if (operator.equals("+")) {
                    numbers.set(i,  numbers.get(i) + numbers.get(i + 1));
                    numbers.remove(i + 1);
                    operators.remove(i);
                } else if (operator.equals("-")) {
                    numbers.set(i, numbers.get(i) - numbers.get(i + 1));
                    numbers.remove(i + 1);
                    operators.remove(i);
                }
            }


            System.out.println("O U T P U T :> " + numbers.get(0));
        }
        return String.valueOf(numbers.get(0));

    }

    public static int checkExpression(String expr) {
//        System.out.println("Matching Exp ..." + expr);
        if (expr.matches("^\\s*\\d+\\s*[+\\-*/]\\s*\\d+\\s*$")) {
            return 1;
        } else if (expr.matches("^\\d+(\\.\\d+)?(\\s*[\\+\\-\\*/]\\s*\\d+(\\.\\d+)?)*$")) {
            return 2;
        } else if (expr.matches("^\\d+(\\.\\d+)?(\\s*[\\+\\-\\*/]\\s*(\\d+(\\.\\d+)?|\\(\\d+(\\.\\d+)?(\\s*[\\+\\-\\*/]\\s*\\d+(\\.\\d+)?)*\\)))*$")) {
            return 3;
        } else if (expr.matches("^(sqrt\\(\\d+(\\.\\d+)?\\)|\\d+(\\.\\d+)?)(\\s*[\\+\\-\\*/\\^]\\s*(sqrt\\(\\d+(\\.\\d+)?\\)|\\d+(\\.\\d+)?))*$")) {
            return 4;
        } else if (expr.matches("^\\s*\\d+\\s*[+\\-*/]\\s*\\d+\\s*M\\+\\s*$\n")) {
            return 5;
        } else {
            return 3;
        }
    }


}