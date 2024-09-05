package problems.advancedcalculator;


import java.util.*;

import static problems.advancedcalculator.Addition.performAddition;
import static problems.advancedcalculator.Division.performDivision;
import static problems.advancedcalculator.Multiplication.performMultiplication;
import static problems.advancedcalculator.Subtraction.performSubtraction;

public class Calculator {
    static Map<Integer, String> tuplesLinkedHashMap = new LinkedHashMap<>();

    //    static List<Double> inMemoryStorage = new ArrayList<>();
    static Stack<Double> inMemoryStorageStackImplementation = new Stack<>();

    static List<Integer> keysToRemove = new ArrayList<>();

    public static Double calculate(String userInput) {

        try {
            if (userInput == null || userInput.trim().isEmpty()) {
                System.out.println("Error: Input is empty or null");
                return null;
            }

            if (userInput.contains("sqrt")) {
                return AdvancedMathmatecialOperation.calculateSquareRoot(userInput);
            }

            if (userInput.contains("^")) {
                return AdvancedMathmatecialOperation.calculateExponential(userInput);
            }

            if (userInput.contains("M+")) {
                String[] split = userInput.trim().split("M\\+");
                String output = performCalculation(getOperation(split[0]), Utility.convertToDoubleArray(split[0].split("\\+|\\-|\\*|\\/")));
                inMemoryStorageStackImplementation.add(Double.parseDouble(output));
                return Double.parseDouble(output);
            }

            String tuple = " ";
            int openIndex;
            int closeIndex;
            String substring;
            userInput = userInput.replaceAll("\\s+", "");
            String[] split = userInput.split("\\+|\\-|\\*|\\/");

            //Checking the input to make it tuple or not. Handling the operation normally for simpler input without making tuples and using LinkedHashMap
            if (!(split.length > 2)) {
                String resultedValue = performCalculation(getOperation(userInput), Utility.convertToDoubleArray(userInput.split("\\+|\\-|\\*|\\/")));
                return Double.parseDouble(resultedValue);
            }

            String[] inputArray = userInput.split("");

            int mapIndex = 0;
            if (userInput.contains("(") || userInput.contains(")")) {
                openIndex = userInput.indexOf("(");
                closeIndex = userInput.indexOf(")");
                substring = userInput.substring(openIndex, closeIndex + 1);
                for (int j = 0; j < openIndex; j = j + 2) {
                    if (j + 1 < userInput.length()) {
                        tuple = inputArray[j] + inputArray[j + 1] + (inputArray[j + 2].contains("(") ? inputArray[j + 3] : inputArray[j + 2]);
                        tuplesLinkedHashMap.put(mapIndex, tuple);
                        mapIndex++;
                    }
                }
                tuplesLinkedHashMap.put(mapIndex, substring);
                mapIndex++;
                for (int j = closeIndex + 1; j < inputArray.length; j = j + 2) {
                    if (j + 1 < userInput.length()) {
                        tuple = ((j == closeIndex + 1) ? inputArray[j - 2] : inputArray[j - 1]) + inputArray[j] + inputArray[j + 1];
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

            //process on the basis of precedenceLevel
            String result = processOperationsByPrecedence(tuplesLinkedHashMap);
            System.out.println("output" + result);
            tuplesLinkedHashMap.clear();
            return Double.parseDouble(result);
        } catch (NumberFormatException e) {
            System.out.println(e);
            return null;
        }
    }

    private static String processOperationsByPrecedence(Map<Integer, String> tuplesLinkedHashMap) {
        String output = null;

        String[] iterator = {"(", "/", "*", "+", "-"};
        if (tuplesLinkedHashMap.isEmpty()) return "";

        //Using recursion for the different operation as provided by the iterator.
        for (String i : iterator) {
            output = precedenceLevelCheckAndPerformingOperation(i);
            if (tuplesLinkedHashMap.size() < 2 && tuplesLinkedHashMap.get(0).contains(i)) {
                break;
            }
        }
        return output;
    }

    //updating nodes of hashMap
    private static void updateHashMapTable(List<Integer> keysToRemove) {
        if (!keysToRemove.isEmpty()) {
            Integer key = keysToRemove.get(0);

            if (key != tuplesLinkedHashMap.size() - 1) { //not the last key index of hashmap
                for (int i = key; i < tuplesLinkedHashMap.size() - 1; i++) {
                    tuplesLinkedHashMap.put(i, tuplesLinkedHashMap.get(i + 1));
                }
                tuplesLinkedHashMap.remove(tuplesLinkedHashMap.size() - 1);
            } else {
                tuplesLinkedHashMap.remove(key);
            }
            keysToRemove.remove(0);
        }
    }

    public static String precedenceLevelCheckAndPerformingOperation(String operatorLevel) {
        String output = "";
        for (Map.Entry<Integer, String> entry : tuplesLinkedHashMap.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            char operation = getOperation(entry.getValue());
            if (value.contains(operatorLevel)) {
                if (operatorLevel.contains("(")) {
                    value = value.substring(1, value.length() - 1);
                }
                output = performCalculation(operation, Utility.convertToDoubleArray(value.split("\\+|\\-|\\*|\\/")));
                if (tuplesLinkedHashMap.size() < 2) {
                    return output;
                }
                performMappingOperation(key, output, operation);
                keysToRemove.add(entry.getKey());
            }
        }
        updateHashMapTable(keysToRemove);
        return output;
    }

    //implementing sir algorithm
    public static void performMappingOperation(Integer key, String output, char operation) {
        //updating upward node. Need to update for non zero key(down to up)
        if (key != 0) {
            String replace = tuplesLinkedHashMap.get(key - 1);
            int operationIndex = problems.advancedcalculator.Utility.getOperationIndex(replace);
            String substring = replace.substring(0, operationIndex + 1);
            tuplesLinkedHashMap.put(key - 1, substring.concat(output));
        }

        //updating the below node. Need to perform for last element (up to down)
        if (!((tuplesLinkedHashMap.size() - key) == 1)) {
            if (tuplesLinkedHashMap.get(key + 1) == null) {
                int operationIndex = problems.advancedcalculator.Utility.getOperationIndex(tuplesLinkedHashMap.get(key + 2));
                String updatedValue = tuplesLinkedHashMap.get(key + 2).substring(operationIndex);
                tuplesLinkedHashMap.put(key + 2, output.concat(updatedValue));
            } else {
                int operationIndex = problems.advancedcalculator.Utility.getOperationIndex(tuplesLinkedHashMap.get(key + 1));
                String updatedValue = tuplesLinkedHashMap.get(key + 1).substring(operationIndex);
                tuplesLinkedHashMap.put(key + 1, output.concat(updatedValue));
            }
        }
    }

    private static String performCalculation(char operation, double[] numbers) {
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
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exit From Calculator");
                return;
            }

            //Calulation part
            calculate(userInput);
        }
    }

    public static double recallMemory() {
//        return inMemoryStorage.get(inMemoryStorage.size() - 1);
        return inMemoryStorageStackImplementation.get(inMemoryStorageStackImplementation.size() - 1);
    }

    public static void storeInMemory(double valuToBeStored) {
//        if (inMemoryStorage.size() > 4) {
//            inMemoryStorage.remove(0);
//            inMemoryStorage.add(valuToBeStored);
//            List<Double> shiftedResult = problems.advancedcalculator.Utility.shiftInputArrayList(inMemoryStorage, 1);
//            inMemoryStorage = shiftedResult;
//            return;
//        }
//        inMemoryStorage.add(valuToBeStored);

        if (inMemoryStorageStackImplementation.size() > 4) {
            inMemoryStorageStackImplementation.remove(inMemoryStorageStackImplementation.get(0));
            inMemoryStorageStackImplementation.push(valuToBeStored);
            List<Double> shiftedResult = problems.advancedcalculator.Utility.shiftInputArrayList(inMemoryStorageStackImplementation, 1);
            inMemoryStorageStackImplementation.clear();
            for (Double result : shiftedResult) {
                inMemoryStorageStackImplementation.push(result);
            }
            return;
        }
        inMemoryStorageStackImplementation.push(valuToBeStored);
    }

    public static void clearMemory() {
//        inMemoryStorage.clear();
        inMemoryStorageStackImplementation.clear();
    }

    public static String recallAllMemory() {
        String result = "Stored values: ";
        if (inMemoryStorageStackImplementation.isEmpty()) {
            return "No values stored in memory.";
        }
//        int size = inMemoryStorageStackImplementation.size();
        for (int i = 0; i < inMemoryStorageStackImplementation.size(); i++) {
            String concat = !(inMemoryStorageStackImplementation.size() - i == 1) ? ", " : "";
            result += inMemoryStorageStackImplementation.get(i) + concat;
        }
        return result;
    }
}
