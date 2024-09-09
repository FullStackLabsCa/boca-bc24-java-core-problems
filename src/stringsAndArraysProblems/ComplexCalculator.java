package stringsAndArraysProblems;

import java.util.*;

import static java.lang.Double.parseDouble;

public class ComplexCalculator {

    public static void main(String[] args) {
        LinkedHashMap<Integer, String> originalEquationTuplesMap = new LinkedHashMap<>();

        // Define the expression string
        String expression = "100.0-(39.5+49.5)*2.5+80/2";

        final List<String> operatorsList = extractOperatorsFromExpression(expression);

        final List<String> operandsList = extractOperandsFromExpression(expression);

        originalEquationTuplesMap = buildTupleMap(operatorsList, operandsList);

        System.out.println("\nOriginal Equation Tuples: " + originalEquationTuplesMap + "\n\n");







//        // Process parenthesis
//        LinkedHashMap<Integer, String> parenthesisMap = processForParenthesis(originalEquationTuplesMap);
//        System.out.println("parenthesis Done Map: " + parenthesisMap);

//
//        LinkedHashMap<Integer, String> filteredForDivMap = filterMapForOperators(originalEquationTuplesMap);
//        System.out.println("Filtered Map to Perform Division (Without Results): " + filteredForDivMap);
//
//        // Process division
//        LinkedHashMap<Integer, String> divisionMap = processForDivision(filteredForDivMap);
//        System.out.println("Division Done Map: " + divisionMap);
//
//        // Filter map for multiplication
//        LinkedHashMap<Integer, String> filteredForMulMap = filterMapForOperators(divisionMap);
//        System.out.println("Filtered Map to Perform Multiplication (Without Results): " + filteredForMulMap);
//
//        // Process multiplication
//        LinkedHashMap<Integer, String> multiplyMap = processForMultiplication(filteredForMulMap);
//        System.out.println("Multiplication Done Map: " + multiplyMap);
//
//        // Filter map for addition
//        LinkedHashMap<Integer, String> filteredForAddMap = filterMapForOperators(multiplyMap);
//        System.out.println("Filtered Map to Perform Addition (Without Results): " + filteredForAddMap);
//
//        // Process addition
//        LinkedHashMap<Integer, String> additionMap = processForAddition(filteredForAddMap);
//        System.out.println("Addition Done Map: " + additionMap);
//
//        // Filter map for addition
//        LinkedHashMap<Integer, String> filteredForSubMap = filterMapForOperators(additionMap);
//        System.out.println("Filtered Map to Perform Subtraction (Without Results): " + filteredForSubMap);
//
//        // Process addition
//        LinkedHashMap<Integer, String> subtractMap = processForSubtraction(filteredForSubMap);
//        System.out.println("Subtraction Done Map: " + subtractMap);

    }

    private static LinkedHashMap<Integer, String> buildTupleMap(List<String> operatorsList, List<String> operandsList) {
        LinkedHashMap<Integer, String> tupleMap = new LinkedHashMap<>();
        StringBuilder tupleBuilder;
        int mapIndex = 0; // Track index for tupleMap
        int operandIndex = 0; // Track the current operand index

        for (int i = 0; i < operatorsList.size(); i++) {
            final String operator = operatorsList.get(i);

            // Make sure operandIndex doesn't exceed the size of operandsList
            if (operandIndex >= operandsList.size()) {
                break;
            }

            if (operandsList.get(operandIndex).startsWith("(")) {
                // Treat the entire parentheses content as one operand
                String operand1 = operandsList.get(operandIndex);
                operandIndex++; // Move to next operand

                // Check that there is still a next operand in bounds
                if (operandIndex < operandsList.size()) {
                    String operand2 = operandsList.get(operandIndex);
                    tupleBuilder = new StringBuilder();
                    String tuple = tupleBuilder.append(operand1).append(operator).append(operand2).toString();
                    tupleMap.put(mapIndex++, tuple);
                }
            } else {
                // Normal operation outside parentheses
                String operand1 = operandsList.get(operandIndex);

                // Make sure operandIndex + 1 is within bounds
                if (operandIndex + 1 < operandsList.size()) {
                    String operand2 = operandsList.get(operandIndex + 1);
                    tupleBuilder = new StringBuilder();
                    String tuple = tupleBuilder.append(operand1).append(operator).append(operand2).toString();
                    tupleMap.put(mapIndex++, tuple);
                    operandIndex++;
                }
            }
        }

        return tupleMap;
    }







    private static List<String> extractOperandsFromExpression(String expression) {
        List<String> operandsList = new ArrayList<>();
        StringBuilder operand = new StringBuilder();
        boolean insideParentheses = false;

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                insideParentheses = true; // Start collecting inside parentheses
                operand.append(ch); // Include the opening parenthesis
            } else if (ch == ')') {
                insideParentheses = false; // Finish collecting inside parentheses
                operand.append(ch); // Include the closing parenthesis
                operandsList.add(operand.toString()); // Add the entire expression inside parentheses
                operand = new StringBuilder(); // Clear buffer
            } else if (insideParentheses || Character.isDigit(ch) || ch == '.') {
                operand.append(ch); // Collect everything inside parentheses or digits/decimal
            } else {
                if (operand.length() > 0) {
                    operandsList.add(operand.toString()); // Add operand before operator
                    operand = new StringBuilder(); // Clear buffer
                }
            }
        }

        // Add last operand if any
        if (operand.length() > 0) {
            operandsList.add(operand.toString());
        }

        return operandsList;
    }



    private static List<String> extractOperatorsFromExpression(String expression) {
        List<String> operatorsList = new ArrayList<>();
        boolean insideParentheses = false;

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                insideParentheses = true; // Ignore operators inside parentheses
            } else if (ch == ')') {
                insideParentheses = false;
            } else if (!Character.isDigit(ch) && !insideParentheses) {
                operatorsList.add(String.valueOf(ch)); // Only collect operators outside parentheses
            }
        }

        return operatorsList;
    }


//
//    private static LinkedHashMap<Integer, String> processForParenthesis(LinkedHashMap<Integer, String> originalMap) {
//        LinkedHashMap<Integer, String> parenthesisMap = new LinkedHashMap<>(originalMap);
////        ArrayList<String> combinedTuples = new ArrayList<>();
////
////        // Traverse the map to handle parentheses
////        int i = 0;
////        while (i < parenthesisMap.size()) {
// //           String value = parenthesisMap.get(i);
////
////            // Check for opening parenthesis
////            if (value.contains("(")) {
////                int openIndex = value.indexOf('(');
////                int closeIndex = value.indexOf(')', openIndex);
////
////            }
//
//        return parenthesisMap;
//    }
//
//
//    private static LinkedHashMap<Integer, String> processForDivision(LinkedHashMap<Integer, String> filteredForDivMap) {
//        String divOperator = "/";
//        LinkedHashMap<Integer, String> divisionMap = new LinkedHashMap<>(filteredForDivMap);
//        for (Map.Entry<Integer, String> entry : divisionMap.entrySet()) {
//            String divTuple = entry.getValue();
//
//            if (divTuple.contains(divOperator)) {
//                String[] arrOfStr = divTuple.split("[/]");
//                double operand1 = parseDouble(arrOfStr[0]);
//                double operand2 = parseDouble(arrOfStr[1]);
//                // Perform the division
//                int result = div(operand1, operand2);
//                final Integer entryKey = entry.getKey();
//                divisionMap.put(entryKey, String.valueOf(result));
//
//
//                // Update the previous tuple
//                if (entryKey >= 0) {
//                    Integer previousKey = entryKey - 1;
//                    String previousTuple = divisionMap.get(previousKey);
//                    if (previousTuple != null && previousTuple.length() > 1) {
//                        String previousTupleReplacement = previousTuple.substring(0, 2) + result;
//                        divisionMap.put(previousKey, previousTupleReplacement);
//                    }
//
//                    // Update the next tuple
//                    if (entryKey < divisionMap.size() - 1) {
//                        Integer nextKey = entryKey + 1;
//                        String nextTuple = divisionMap.get(nextKey);
//                        if (nextTuple != null && nextTuple.length() > 1) {
//                            String nextTupleReplacement = result + nextTuple.substring(1);
//                            divisionMap.put(nextKey, nextTupleReplacement);
//                        }
//                    }
//
//                }
//            }
//
//        }
//
//
//        return divisionMap;
//    }
//
//// The same pattern will apply to multiplication, addition, and subtraction processes:
//
//    private static LinkedHashMap<Integer, String> processForMultiplication(LinkedHashMap<Integer, String> filteredForMulMap) {
//        String mulOperator = "*";
//        LinkedHashMap<Integer, String> multiplyMap = new LinkedHashMap<>(filteredForMulMap);
//        for (Map.Entry<Integer, String> entry : multiplyMap.entrySet()) {
//            String mulTuple = entry.getValue();
//
//            if (mulTuple.contains(mulOperator)) {
//                String[] arrOfStr = mulTuple.split("[*]");
//                double operand1 = parseDouble(arrOfStr[0]);
//                double operand2 = parseDouble(arrOfStr[1]);
//                // Perform the multiplication
//                int result = mul(operand1, operand2);
//                final Integer entryKey = entry.getKey();
//                multiplyMap.put(entryKey, String.valueOf(result));
//
//                // Update the previous tuple
//                if (entryKey >= 0) {
//                    Integer previousKey = entryKey - 1;
//                    String previousTuple = multiplyMap.get(previousKey);
//                    if (previousTuple != null && previousTuple.length() > 1) {
//                        String previousTupleReplacement = previousTuple.substring(0, 2) + result;
//                        multiplyMap.put(previousKey, previousTupleReplacement);
//                    }
//
//                    // Update the next tuple
//                    if (entryKey < multiplyMap.size() - 1) {
//                        Integer nextKey = entryKey + 1;
//                        String nextTuple = multiplyMap.get(nextKey);
//                        if (nextTuple != null && nextTuple.length() > 1) {
//                            String nextTupleReplacement = result + nextTuple.substring(1);
//                            multiplyMap.put(nextKey, nextTupleReplacement);
//                        }
//                    }
//
//                }
//            }
//        }
//        return multiplyMap;
//    }
//
//    private static LinkedHashMap<Integer, String> processForAddition(LinkedHashMap<Integer, String> filteredForAddMap) {
//        String addOperator = "+";
//        LinkedHashMap<Integer, String> additionMap = new LinkedHashMap<>(filteredForAddMap);
//        for (Map.Entry<Integer, String> entry : additionMap.entrySet()) {
//            String addTuple = entry.getValue();
//
//            if (addTuple.contains(addOperator)) {
//                String[] arrOfStr = addTuple.split("[+]");
//                double operand1 = parseDouble(arrOfStr[0]);
//                double operand2 = parseDouble(arrOfStr[1]);
//                // Perform the addition
//                int result = add(operand1, operand2);
//                final Integer entryKey = entry.getKey();
//                additionMap.put(entryKey, String.valueOf(result));
//
//                // Update the previous tuple
//                if (entryKey >= 0) {
//                    Integer previousKey = entryKey - 1;
//                    String previousTuple = additionMap.get(previousKey);
//                    if (previousTuple != null && previousTuple.length() > 1) {
//                        String previousTupleReplacement = previousTuple.substring(0, 2) + result;
//                        additionMap.put(previousKey, previousTupleReplacement);
//                    }
//
//                    // Update the next tuple
//                    if (entryKey < additionMap.size() - 1) {
//                        Integer nextKey = entryKey + 1;
//                        String nextTuple = additionMap.get(nextKey);
//                        if (nextTuple != null && nextTuple.length() > 1) {
//                            String nextTupleReplacement = result + nextTuple.substring(1);
//                            additionMap.put(nextKey, nextTupleReplacement);
//                        }
//                    }
//
//
//                }
//            }
//        }
//        return additionMap;
//    }
//
//    private static LinkedHashMap<Integer, String> processForSubtraction(LinkedHashMap<Integer, String> filteredForSubMap) {
//        String subOperator = "-";
//        LinkedHashMap<Integer, String> subtractMap = new LinkedHashMap<>(filteredForSubMap);
//
//        for (Map.Entry<Integer, String> entry : subtractMap.entrySet()) {
//            String subTuple = entry.getValue();
//
//            if (subTuple.contains(subOperator)) {
//                String[] arrOfStr = subTuple.split("[-]");
//                double operand1 = parseDouble(arrOfStr[0]);
//                double operand2 = parseDouble(arrOfStr[1]);
//                // Perform the subtraction
//                int result = sub(operand1, operand2);
//                final Integer entryKey = entry.getKey();
//                subtractMap.put(entryKey, String.valueOf(result));
//
//
//                // Update the previous tuple
//                if (entryKey >= 0) {
//                    Integer previousKey = entryKey - 1;
//                    String previousTuple = subtractMap.get(previousKey);
//                    if (previousTuple != null && previousTuple.length() > 1) {
//                        String previousTupleReplacement = previousTuple.substring(0, 2) + result;
//                        subtractMap.put(previousKey, previousTupleReplacement);
//                    }
//
//                    // Update the next tuple
//                    if (entryKey < subtractMap.size() - 1) {
//                        Integer nextKey = entryKey + 1;
//                        String nextTuple = subtractMap.get(nextKey);
//                        if (nextTuple != null && nextTuple.length() > 1) {
//                            String nextTupleReplacement = result + nextTuple.substring(1);
//                            subtractMap.put(nextKey, nextTupleReplacement);
//                        }
//                    }
//
//
//                }
//            }
//        }
//        return subtractMap;
//    }
//
//    //method to filter map from the result and update the neighbouring keys nodes
//
//    private static LinkedHashMap<Integer, String> filterMapForOperators(LinkedHashMap<Integer, String> originalMap) {
//        LinkedHashMap<Integer, String> filteredMap = new LinkedHashMap<>();
//        int newKey = 0;
//        for (Map.Entry<Integer, String> entry : originalMap.entrySet()) {
//            String value = entry.getValue();
//            // Check if the value contains an operator
//            if (value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")) {
//                filteredMap.put(newKey, value);
//                newKey++;
//            }
//        }
//        return filteredMap;
//    }
//
//
//    //methods to perform arithmetic operations
//    public static int div(double num1, double num2) {
//        if (num2 == 0) {
//            throw new ArithmeticException("Division by zero");
//        }
//        return (int) num1 / (int) num2;
//    }
//
//    public static int mul(double num1, double num2) {
//        return (int) num1 * (int) num2;
//    }
//
//    public static int add(double num1, double num2) {
//        return (int) num1 + (int) num2;
//    }
//
//    public static int sub(double num1, double num2) {
//        return (int) num1 - (int) num2;
//
//    }
//
//
//    ///test methods
//
//    public static double calculate(String expression) {
//        return 0.0;
//    }
//
//    public static void clearMemory() {
//    }
//
//    public static double recallMemory() {
//        return 0.0;
//    }
//
//    public static void storeInMemory(double v) {
//    }
//
//    public static String recallAllMemory() {
//        return "null";
//    }


}

