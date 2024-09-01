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

        String input = "8 + 5 M+";
//        String input = "3 + 5 * 2 - 4 / 2";
//        String input = "3 + 5 * ( 9 / 3 ) / 2";
//        String input = "3 + (2 * 4) - ( 4 / 2 + 3) + 5 ";
//        String input = "sqrt(25) + 3 ^ 2";
//        String input = "sqrt(16)","2 ^ 3";
//        String input = "8 + 5";


        try {
            if (input.contains("M+")) {
                String pattern = "M\\+";
                input = input.replaceAll(pattern, "").trim();
                memoryOperation = true;
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

    public static String calculate(String input) {

        String output = "";
        if (input == null || input.equalsIgnoreCase("") || input.trim().isEmpty()) {
            return "Error: Input is empty or null";
        } else if (checkExpression(input) != 0) {
            switch (checkExpression(input)) {
                case 1:
                    if (checkExpression(input) == 0) {
                        String[] parts = input.split("\\s*[+\\-*/]\\s*");
                        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                            return "Error: Invalid input format";
                        } else {
                            return ERROR_MSG;
                        }
                    }
                    output = basicOperation(input);
                case 2:
                    output = multiOperation(input);
                    break;
                case 3:
                    output = paranthesisOperation(input);
                    break;
                case 4:
                    output = AdvancedOperation(input);
                    break;
                case 5:
//                    output = memoryFunction(input).toString();
//                    break;
            }
        }

        if(memoryOperation){
            calculatorMemory.add(Double.parseDouble(output));
        }
        return output;
    }

    private static String basicOperation(String input) {
        String returnString = "";
        String[] parts = input.trim().split("\\s*[+\\-*/]\\s*");
        float addendum1 = Integer.parseInt(parts[0]);
        float addendum2 = Integer.parseInt(parts[1]);
        String operator = input.replaceAll("[^+\\-*/]", "").trim();
        switch (operator) {
            case "/":
                if (addendum2 == 0) {
                    return "Error: Cannot divide by zero";
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
        return returnString;
    }


    private static void memoryFunction(String input) {
        if (memorySize > MAX_MEMORY) {
            calculatorMemory.set(0, Double.parseDouble(input));
        } else {
            calculatorMemory.add(Double.parseDouble(input));
            memorySize++;
        }
    }

    public static void clearMemory() {
        calculatorMemory = new ArrayList<>();
    }

    public static Double recallMemory() {
        return calculatorMemory.get(calculatorMemory.size() - 1);

    }

    public static String recallAllMemory() {
        String output = "Stored values: ";
        if (calculatorMemory.size() == 0) {
            return "No values stored in memory.";
        } else {
            for (Double value : calculatorMemory) {
                if (calculatorMemory.indexOf(value) == calculatorMemory.size() - 1) {
                    output += value;
                } else {
                    output += value + ", ";
                }
            }
        }

        return output;
    }

    private static String AdvancedOperation(String input) {

        input = input.replaceAll(" ", "");
        while (input.contains("sqrt")) {
            int start = input.indexOf("sqrt");
            int end = input.indexOf(')', start);
            String numberStr = input.substring(start + 5, end);
            int number = Integer.parseInt(numberStr);
            int result = (int) Math.sqrt(number);
            input = input.substring(0, start) + result + input.substring(end + 1);
        }

        return multiOperation(input);
    }

    private static String paranthesisOperation(String input) {

        System.out.println("Parenthesis Operation ...");

        input = input.replaceAll(" ", "");
        StringBuilder tempString = new StringBuilder();

        for (int lastIndex = 0; lastIndex < input.trim().length(); lastIndex++) {
            int startIndex = lastIndex;
            if (input.charAt(lastIndex) == '(') {
                while (input.charAt(lastIndex) != ')') {
                    tempString.append(input.charAt(lastIndex + 1));
                    lastIndex++;
                }
                tempString = tempString.deleteCharAt(tempString.length() - 1);
                String operationVal = multiOperation(tempString.toString());
                input = input.replace(input.substring(startIndex, lastIndex + 1), operationVal);

            }
        }
        if (input.contains("Error")) {
            return ERROR_MSG;
        } else {
            return multiOperation(input.toString());
        }
    }

    private static String multiOperation(String input) {
        System.out.println("Multi Operation start ...." + input);

        List<Character> operators = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        for (Character character : input.toCharArray()) {
            if (character != ' ') {
                if (Character.isDigit(character)) {
                    numbers.add(Integer.parseInt(character.toString()));
                } else {
                    operators.add(character);
                }
            }
        }

        if (!input.isEmpty()) {

            int power = 0;
            while (power < operators.size()) {
                if (operators.get(power) == '^') {
                    int base = numbers.get(power);
                    int exponent = numbers.get(power + 1);
                    numbers.set(power, (int) Math.pow(base, exponent));
                    numbers.remove(power + 1);
                    operators.remove(power);
                } else {
                    power++;
                }
            }

            int i = 0;
            while (i < operators.size()) {

                if (operators.get(i) == '/') {
                    if (numbers.get(i + 1) == 0) {
                        return "Error: Cannot divide by zero";
                    } else {
                        numbers.add(i + 1, numbers.get(i) / numbers.get(i + 1));
                        numbers.remove(i);
                        numbers.remove(i + 1);
                        operators.remove(i);
                    }

                } else if (operators.get(i) == '*') {
                    numbers.add(i + 1, numbers.get(i) * numbers.get(i + 1));
                    numbers.remove(i);
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
                    int result = numbers.get(i) + numbers.get(i + 1);
                    numbers.set(i, (int) result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                } else if (operator.equals("-")) {
                    int result = numbers.get(i) - numbers.get(i + 1);
                    numbers.set(i, (int) result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                }
            }

            input = String.valueOf((int) numbers.get(0));
            System.out.println("O U T P U T :> " + input);
        }
        return input;

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