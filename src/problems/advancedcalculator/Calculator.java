package problems.advancedcalculator;

import problems.array.Utility;

import java.util.*;

import static problems.advancedcalculator.Addition.*;
import static problems.advancedcalculator.Division.performDivision;
import static problems.advancedcalculator.Multiplication.performMultiplication;
import static problems.advancedcalculator.Subtraction.performSubtraction;

public class Calculator {
    static LinkedHashMap<Integer, String> tuplesLinkedHashMap = new LinkedHashMap<>();

    public static void clearMemory() {
    }

    public String calculate(String userInput) {

        try {
            if (userInput == null || userInput.trim().isEmpty()) return "Error: Input is empty or null";
            String tuple = " ";
            int openIndex;
            int closeIndex;
            String substring;
            String[] inputArray = userInput.split("");

            int mapIndex = 0;
            if (userInput.contains("(") || userInput.contains(")")) {
                openIndex = userInput.indexOf("(");
                closeIndex = userInput.indexOf(")");
                substring = userInput.substring(openIndex, closeIndex + 1);
                for (int j = 0; j < openIndex; j = j + 2) {
                    tuple = inputArray[j] + inputArray[j + 1] + (inputArray[j + 2].contains("(") ? "_" : inputArray[j + 2]);
                    tuplesLinkedHashMap.put(mapIndex, tuple);
                    mapIndex++;
                }
                tuplesLinkedHashMap.put(mapIndex, substring);
                mapIndex++;
                for (int j = closeIndex + 1; j < userInput.length(); j = j + 2) {
                    if (j + 1 < userInput.length()) {
                        tuple = "_" + inputArray[j] + inputArray[j + 1];
                        tuplesLinkedHashMap.put(mapIndex, tuple);
                        mapIndex++;
                    }
                }

                System.out.println("map" + tuplesLinkedHashMap);

            } else {
                for (int i = 0; i < inputArray.length; i = i + 2) {
                    if (i + 2 < inputArray.length) {
                        tuple = inputArray[i] + inputArray[i + 1] + inputArray[i + 2];
                        tuplesLinkedHashMap.put(mapIndex, tuple);
                        mapIndex++;
                    }
                }
            }

            //For performing the operation in linkedHashmap
            for (Map.Entry<Integer, String> entry : tuplesLinkedHashMap.entrySet()) {
                char operationType = 0;
                Integer key = entry.getKey();
                String value = entry.getValue();

                precedenceLevelCheck(entry.getValue());

                System.out.println("key" + key + "Val" + value);

                char operation = getOperation(entry.getValue());
                if (value.contains("(")) {
                    String value1 = value.substring(1, value.length() - 1);
                    performMappingOperation(key, value1, operation);
                    if(value.contains("/")){
                        performMappingOperation(key, value, operation);
                    }


                } else if (value.contains("/")) {
                    performMappingOperation(key, value, operation);
                } else if (value.contains("*")) {
                    performMappingOperation(key, value, operation);
                } else if (value.contains("+")) {
                    performMappingOperation(key, value, operation);
                } else if (value.contains("-")) {
                    performMappingOperation(key, value, operation);
                }

            }


        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return "";
    }

    public static void performMappingOperation(Integer key, String value, char operation) {
        String output = performOperation(operation, Utility.convertToIntegerArray(value.split("\\+|\\-|\\*|\\/")));
        if (key > 0 && key < tuplesLinkedHashMap.size()) {
            String replace = tuplesLinkedHashMap.get(key - 1).replace('_', output.charAt(0));
            tuplesLinkedHashMap.put(key - 1, replace);
            tuplesLinkedHashMap.remove(key);
            String update = tuplesLinkedHashMap.get(key + 1).replace('_', output.charAt(0));
            tuplesLinkedHashMap.put(key + 1, update);
        }
    }

    public static Integer precedenceLevelCheck(String input) {
        if (input.equals("(")) return 5;
        if (input.equals("/")) return 4;
        if (input.equals("*")) return 3;
        if (input.equals("+")) return 2;
        if (input.equals("-")) return 1;
        return 0;
    }


    public static boolean operatorCheck(String checkElement) {
        return Objects.equals(checkElement, "+") || Objects.equals(checkElement, "-") || Objects.equals(checkElement, "*") || Objects.equals(checkElement, "/");
    }


    private static String performOperation(char operation, int[] numbers) {
        switch (operation) {
            case '+' -> {
                return performAddition(numbers);
            }
            case '-' -> {
                return performSubtraction(numbers);
            }
            case '*' -> {
                return performMultiplication(numbers);
            }
            case '/' -> {
                return performDivision(numbers);
            }
        }
        return null;
    }


    public static double paranthesisOperation(List<Integer> range, char[] input) {
        int[] element = new int[2];
        char operator = 0;
        int j = 0;
        for (int i = range.get(0) + 1; i < range.get(1); i++) {
            if (input[i] == '+' || input[i] == '-' || input[i] == '/' || input[i] == '*') {
                operator = input[i];
            } else {
                element[j] = (Integer.parseInt(String.valueOf(input[i])));
                j++;
            }
        }

        return Double.parseDouble(performOperation(operator, element));

    }


    private static char getOperation(String userInput) {
       char operation = 0;
        if (userInput.contains("+")) {
            operation = '+';
        } else if (userInput.contains("-")) {
            operation = '-';
        } else if (userInput.contains("*")) {
            operation = '*';
        } else if (userInput.contains("/")) {
            operation = '/';
        }
        return operation;
    }

    public static void main(String[] args) {
        while (true) {
            //Taking User Input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the operands with operation");
            String userInput = scanner.nextLine().trim();

            //Checking if user wants to exit
            if (userInput.equalsIgnoreCase("x")) {
                System.out.println("Exit From Calculator");
                return;
            }

            //Calulation part
            Calculator calculator = new Calculator();
            calculator.calculate(userInput);
        }
    }

}
