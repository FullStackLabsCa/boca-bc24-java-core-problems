package problems.string.advanced.calculator;

import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Math.*;

@SuppressWarnings("java:S106")
public class Calculator {
    public static double operandResult = 0;
    private static Stack<Double> memoryStack = new Stack<>();
    private static boolean resultStoreInMemory = false;


    public static double calculate(String str) throws ArithmeticException {
        if (str == null || str.isEmpty()) {
            System.out.println("Error: Input is empty or null");
            return 0;
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
        double sqrtResult = 0;
        double exponentResult = 0;

        if (str.contains("M+")) {
            resultStoreInMemory = true;
        }

        if (str.contains("sqrt")) {
            exponentResult = 0;
            String[] partsSqrt = str.split("[()]");
            sqrtResult = sqrt(Double.parseDouble((partsSqrt[1])));
            exponentResult = sqrtResult;
            if (sqrtResult == Double.NaN) {
                throw new ArithmeticException();
            }
//            System.out.println("sqrtResult = " + sqrtResult);
            return exponentResult;
        }

        if (str.contains("^")) {
            String[] partsExponent = str.split("[()]");
            exponentResult = Math.pow(Double.parseDouble((parts[0])), Double.parseDouble((parts[2])));
            System.out.println("exponentResult = " + exponentResult);
            return exponentResult;
        }

//        if (sqrtResult != 0 && exponentResult != 0) {
//            operandResult = sqrtResult + exponentResult;
//            System.out.println("operandResult = " + operandResult);
//        }

        //making tuples and adding to the Map
        for (int i = 0; i < parts.length - 1; i = i + 2) {
            //checking validation if it passes then adding to the Map
            if (parts[parts.length - 1].equals("M+") && i > 1 && resultStoreInMemory) {
//                resultStoreInMemory = false;
                break;
            }
            if (parts[i].equals(" ") || parts[i + 2].equals(" ")) {
                System.out.println("Error: Invalid input format");
            } else {
                tuple = parts[i] + parts[i + 1] + parts[i + 2];
                bracketsMap.put(index, tuple);
                index++;
            }
        }

        //checking brackets
//        System.out.println("bracketsMap= " + bracketsMap);
        for (Map.Entry<Integer, String> entry : bracketsMap.entrySet()) {
            Number k = entry.getKey();
            String v = entry.getValue();
            int previousNodeKey = (int) k - 1;
            int nextNodeKey = (int) k + 1;
            double num1 = 0;
            double num2 = 0;
            String value = v.toString();
            String[] array = value.split("(?<=[-+*/])|(?=[-+*/])");
            String operand = "";
            boolean containOpenCloseBracket = false;

            if (array[0].contains("(") && array[array.length - 1].contains(")")) {
                operand = String.valueOf(array[1]);
                containOpenCloseBracket = true;
                num1 = parseDouble((array[0].replaceAll("[(]", "")));
                num2 = parseDouble((array[2].replaceAll("[)]", "")));
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
                    String[] splitArray = nextNode.split("(?<=[-+*/])|(?=[-+*/])");
                    for (int i = 0; i < splitArray.length; i++) {
                        if (splitArray[i].contains(")") && splitArray[splitArray.length - 1].equals(")")) {
                            valueSplit = splitArray[1] + splitArray[2] + operandResult;
                            break;
                        } else if (splitArray[i].contains(")") && splitArray[1] != ")") {
                            valueSplit = operandResult + splitArray[1] + splitArray[2];
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
//        System.out.println("divisionMap= " + divisionMap);

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

            if (value.contains("/")) {
                containDivisionOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containDivisionOperand) {
                updateNodesAndPerformOperation(divisionMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                break;
            }
        }

        //adding to multiplication map
        indexForAddingToMap = 0;
        for (String entry : divisionMap.values()) {
            multiplyMap.put(indexForAddingToMap, entry);
            indexForAddingToMap++;
        }
//        System.out.println("multiplyMap = " + multiplyMap);

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

            if (value.contains(operand)) {
                containMultiplyOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containMultiplyOperand) {
                updateNodesAndPerformOperation(multiplyMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                break;
            }
        }

        //adding to addition map
        indexForAddingToMap = 0;
        for (String entry : multiplyMap.values()) {
            additionMap.put(indexForAddingToMap, entry);
            indexForAddingToMap++;
        }
//        System.out.println("additionMap = " + additionMap);

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

            if (value.contains("+")) {
                containAdditionOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containAdditionOperand) {
                updateNodesAndPerformOperation(additionMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                break;
            }
        }

        //adding to addition map
        indexForAddingToMap = 0;
        for (String entry : additionMap.values()) {
            subtractionMap.put(indexForAddingToMap, entry);
            indexForAddingToMap++;
        }
//        System.out.println("subtractionMap = " + subtractionMap);

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

            if (value.contains("-")) {
                containSubtractOperand = true;
                num1 = parseDouble(String.valueOf(array[0]));
                num2 = parseDouble(String.valueOf(array[1]));
            }

            if (containSubtractOperand) {
                updateNodesAndPerformOperation(subtractionMap, key, previousNodeKey, nextNodeKey, num1, num2, operand);
                break;
            }
        }

        System.out.println("Result of the given expression: " + operandResult);
        return operandResult;
    }

    public static void updateNodesAndPerformOperation(Map<Integer, String> linkedHashMap, int key, int previousNodeKey, int nextNodeKey, Double num1, Double num2, String operand) {
        operandResult = 0;
        operandResult = performOperation(num1, operand, num2);
        //previousNode logic
        if (previousNodeKey != -1) {
            String previousNode = linkedHashMap.get(previousNodeKey);
            String valueSplit = "";
            String[] splitArray = previousNode.split("(?<=[-+*/])|(?=[-+*/])");
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
                result = divide.getResult();
                break;
            default:
                System.out.println("Only Addition, Subtraction, Multiplication, Division operations are allowed");
        }
        if (resultStoreInMemory) {
            storeInMemory(Double.parseDouble(result));
            resultStoreInMemory = false;
        }
        return Double.parseDouble(result);
    }

    public static void storeInMemory(double value) {
        memoryStack.push(value);
    }

    public static void clearMemory() {
        int size = memoryStack.size();
        for (int i = 0; i < size; i++) {
            memoryStack.pop();
        }
    }

    public static double recallMemory() {
        double value = 0;
        for (double val : memoryStack) {
            value = val;
        }
        return value;
    }

    public static String recallAllMemory() {
        if (memoryStack.isEmpty()) {
            return "No values stored in memory.";
        }
        String memoryValues = "";
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                memoryValues = memoryValues + memoryStack.peek() + ", ";
            } else if (i == 4) {
                memoryValues = memoryValues + memoryStack.get(i);
            } else {
                memoryValues = memoryValues + memoryStack.get(i) + ", ";
            }
        }
        return "Stored values: " + memoryValues;
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
