package problems.string.advanced.calculator;

import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;

@SuppressWarnings("java:S106")
public class Calculator {
    public static void calculate(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Error: Input is empty or null");
            return;
        }

        LinkedHashMap<Integer, String> bracketsMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, String> divisionMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, String> multiplyMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, String> additionMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, String> subtractionMap = new LinkedHashMap<>();

        String[] parts;
        String tuple = "";
        parts = str.split(" ");
        int index = 0;
        int indexForAddingToMap = 0;

        //making tuples and adding to the Map
        for (int i = 0; i < parts.length - 1; i = i + 2) {
            //checking validation if it passes then adding to the Map
            if (parts[i].equals(" ") || parts[i + 2].equals(" ")) {
                System.out.println("Error: Invalid input format");
            } else {
                tuple = parts[i] + parts[i + 1] + parts[i + 2];
                bracketsMap.put(index, tuple);
                index++;
            }
        }

        //checking brackets
        System.out.println("bracketsMap= " + bracketsMap);
        for (Map.Entry<Integer, String> entry : bracketsMap.entrySet()) {
            Number k = entry.getKey();
            String v = entry.getValue();
            int previousNodeKey = (int) k - 1;
            int nextNodeKey = (int) k + 1;
            double num1 = 0;
            double num2 = 0;
            double operandResult = 0;
            String value = v.toString();
            String[] array = value.split("()");
            String operand = "";
            boolean containOpenCloseBracket = false;

            if (array[0].contains("(") && array[4].contains(")")) {
                operand = String.valueOf(array[2]);
                containOpenCloseBracket = true;
                num1 = parseDouble(String.valueOf(array[1]));
                num2 = parseDouble(String.valueOf(array[3]));
            }

            if (containOpenCloseBracket) {
                operandResult = performOperation(num1, operand, num2);
                //previousNode logic
                if (previousNodeKey != -1) {
                    String previousNode = bracketsMap.get(previousNodeKey);
                    String valueSplit = "";
                    String[] splitArray = previousNode.split("(?<=[-+*/])|(?=[-+*/])");
                    for (int i = 0; i < splitArray.length; i++) {
                        if (splitArray[i].contains("(") && splitArray[0] == "(") {
                            valueSplit = splitArray[1] + splitArray[2] + operandResult;
                            break;
                        } else if (splitArray[i].contains("(") && splitArray[0] != "(") {
                            valueSplit = splitArray[0] + splitArray[1] + operandResult;
                            break;
                        }
                    }
                    bracketsMap.put(previousNodeKey, valueSplit);
                }

                //nextNode Logic
                if (nextNodeKey < bracketsMap.size()) {
                    String nextNode = bracketsMap.get(nextNodeKey);
                    String valueSplit = "";
                    String[] splitArray = nextNode.split("");
                    for (int i = 0; i < splitArray.length; i++) {
                        if (splitArray[i].contains(")") && splitArray[splitArray.length - 1].equals(")")) {
                            valueSplit = splitArray[1] + splitArray[2] + operandResult;
                            break;
                        } else if (splitArray[i].contains(")") && splitArray[1] != ")") {
                            valueSplit = operandResult + splitArray[2] + splitArray[3];
                            break;
                        }
                    }
                    bracketsMap.put(nextNodeKey, valueSplit);
                }
                bracketsMap.remove(k);
                break;
            }
        }

        //adding to division map
        for (String entry : bracketsMap.values()) {
            divisionMap.put(indexForAddingToMap, entry);
            indexForAddingToMap++;
        }
        System.out.println("divisionMap= " + divisionMap);

        //checking division
        for (Map.Entry<Integer, String> entry : divisionMap.entrySet()) {
            Number k = entry.getKey();
            String v = entry.getValue();
            int key = entry.getKey();
            int previousNodeKey = (int) k - 1;
            int nextNodeKey = (int) k + 1;
            double num1 = 0;
            double num2 = 0;
            String value = v.toString();
            String[] array = value.split("/");
            String operand = "/";
            boolean containDivisionOperand = false;
            double operandResult = 0;

            if (value.contains("/")) {
                containDivisionOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containDivisionOperand) {
                operandResult = updateNodesAndPerformOperation(divisionMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                break;
            }
        }

        //adding to multiplication map
        indexForAddingToMap = 0;
        for (String entry : divisionMap.values()) {
            multiplyMap.put(indexForAddingToMap, entry);
            indexForAddingToMap++;
        }
        System.out.println("multiplyMap = " + multiplyMap);

        //checking multiplication
        for (Map.Entry<Integer, String> entry : multiplyMap.entrySet()) {
            Number k = entry.getKey();
            String v = entry.getValue();
            int key = entry.getKey();
            int previousNodeKey = (int) k - 1;
            int nextNodeKey = (int) k + 1;
            double num1 = 0;
            double num2 = 0;
            String value = v.toString();
            String[] array = value.split("\\*");
            String operand = "*";
            boolean containMultiplyOperand = false;
            double operandResult = 0;

            if (value.contains(operand)) {
                containMultiplyOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containMultiplyOperand) {
                operandResult = updateNodesAndPerformOperation(multiplyMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                break;
            }
        }

        //adding to addition map
        indexForAddingToMap = 0;
        for (String entry : multiplyMap.values()) {
            additionMap.put(indexForAddingToMap, entry);
            indexForAddingToMap++;
        }
        System.out.println("additionMap = " + additionMap);

        //checking addition
        for (Map.Entry<Integer, String> entry : additionMap.entrySet()) {
            Number k = entry.getKey();
            String v = entry.getValue();
            int key = entry.getKey();
            int previousNodeKey = (int) k - 1;
            int nextNodeKey = (int) k + 1;
            double num1 = 0;
            double num2 = 0;
            String value = v.toString();
            String[] array = value.split("\\+");
            String operand = "+";
            boolean containAdditionOperand = false;
            double operandResult = 0;

            if (value.contains("+")) {
                containAdditionOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containAdditionOperand) {
                operandResult = updateNodesAndPerformOperation(additionMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                System.out.println("operandResult = " + operandResult);
                break;
            }
        }

        //adding to addition map
        indexForAddingToMap = 0;
        for (String entry : additionMap.values()) {
            subtractionMap.put(indexForAddingToMap, entry);
            indexForAddingToMap++;
        }
        System.out.println("subtractionMap = " + subtractionMap);

        //checking subtract
        for (Map.Entry<Integer, String> entry : subtractionMap.entrySet()) {
            Number k = entry.getKey();
            String v = entry.getValue();
            int key = entry.getKey();
            int previousNodeKey = (int) k - 1;
            int nextNodeKey = (int) k + 1;
            double num1 = 0;
            double num2 = 0;
            String value = v.toString();
            String[] array = value.split("-");
            String operand = "-";
            boolean containSubtractOperand = false;
            double operandResult = 0;

            if (value.contains("-")) {
                containSubtractOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containSubtractOperand) {
                operandResult = updateNodesAndPerformOperation(subtractionMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                System.out.println("operandResult = " + operandResult);
                break;
            }
        }
    }

    public static double updateNodesAndPerformOperation(Map<Integer, String> linkedHashMap, int key, int previousNodeKey, int nextNodeKey, Double num1, Double num2, String operand) {
        double operandResult = 0;
        operandResult = performOperation(num1, operand, num2);
        //previousNode logic
        if (previousNodeKey != -1) {
            String previousNode = linkedHashMap.get(previousNodeKey);
            String valueSplit = "";
            String[] splitArray = previousNode.split("");
            for (int i = 0; i < splitArray.length; i++) {
                valueSplit = splitArray[0] + splitArray[1] + operandResult;
                break;
            }
            linkedHashMap.put(previousNodeKey, valueSplit);
        }

        //nextNode Logic
        if (nextNodeKey < linkedHashMap.size()) {
            String nextNode = linkedHashMap.get(nextNodeKey);
            String valueSplit = "";
            String[] splitArray = nextNode.split("(?<=[-+*/])|(?=[-+*/])");
            for (int i = 0; i < splitArray.length; i++) {
                valueSplit = operandResult + splitArray[1] + splitArray[2];
                break;
            }
            linkedHashMap.put(nextNodeKey, valueSplit);
        }
        linkedHashMap.remove(key);
        return operandResult;
    }

    public static double performOperation(double number1, String operand, double number2) {
        String result = "";
        switch (operand) {
            case "+":
                Addition add = new Addition(number1, number2);
                result = add.getResult();
                break;
            case "-":
                Subtraction sub = new Subtraction(number1, number2);
                result = sub.getResult();
                break;
            case "*":
                Multiplication multiply = new Multiplication(number1, number2);
                result = multiply.getResult();
                break;
            case "/":
                Division divide = new Division(number1, number2);
                if (number2 == 0)
                    System.out.println("Error: Cannot divide by zero");
                result = divide.getResult();
                break;
            default:
                System.out.println("Only Addition, Subtraction, Multiplication, Division operations are allowed");
        }
//        System.out.println("Result of two operands: " + result);
        return Double.parseDouble(result);
    }

    public static void main(String[] args) {
        System.out.println("Advanced Calculator");

        while (true) {
            System.out.println("Please enter a values to calculate and enter exit to quit the application");
            Scanner value = new Scanner(System.in);
            String inputValue = value.nextLine();
            if (inputValue.contains("exit")) {
                System.out.println("Exiting from calculator");
                break;
            }
            calculate(inputValue);
        }
    }
}
