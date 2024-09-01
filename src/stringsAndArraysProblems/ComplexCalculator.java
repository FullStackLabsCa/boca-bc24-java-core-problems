package stringsAndArraysProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class ComplexCalculator {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> originalEquationTuplesMap = new LinkedHashMap<>();

        // give my program a string of operation
        String operation = "2+3*8/4-3+5-2";

        // it converts that string into characters array and stores each char in
        // char type array
        char[] operationCharArray = operation.toCharArray();
        ArrayList<Character> arrayOfOperation = new ArrayList<>();
        for (char c : operationCharArray) {
            arrayOfOperation.add(c);
        }

        // now it iterates through that array of characters and
        // pick the 1st three characters
        // then it goes to index three and picks next three characters and so on
        String tuple;
        int count = 0;
        for (int i = 0; i < arrayOfOperation.size() - 2; i += 2) {
            tuple = "" + arrayOfOperation.get(i) + arrayOfOperation.get(i + 1) + arrayOfOperation.get(i + 2);
            // then store those tuples in the stringOperation LinkedHashMap as its values .
            originalEquationTuplesMap.put(count, tuple);
            count++;
        }
        System.out.println("Original Equation Tuples: " + originalEquationTuplesMap);
        // now scan this map for division operator
        final Map<Integer, String> divisionMap = processForDivision(originalEquationTuplesMap);
        System.out.println("division done map: " + divisionMap);
//        // now scan this map for multiply operator
//        final Map<Integer, String> multiplyMap = processForDelete(originalEquationTuplesMap);
//        // now scan this map for addition operator
//        final Map<Integer, String> additionMap = processForDelete(originalEquationTuplesMap);
//        // now scan this map for subtract operator
//        final Map<Integer, String> subtractMap = processForDelete(originalEquationTuplesMap);
//

    }

    private static Map<Integer, String> processForDivision(LinkedHashMap<Integer, String> originalMap) {
        String divOperator = "/";
        Map<Integer, String> divisionMap = new HashMap<>(originalMap);
        for (Map.Entry<Integer, String> entry : divisionMap.entrySet()) {
            String divTuple = entry.getValue();

            if (divTuple.contains(divOperator)) {
                String[] arrOfStr = divTuple.split("[/]");
                double operand1 = parseDouble(arrOfStr[0]);
                double operand2 = parseDouble(arrOfStr[1]);
                // Perform the division
                double result = div(operand1, operand2);
                final Integer entryKey = entry.getKey();
                divisionMap.put(entryKey, String.valueOf(result));
                //
                Integer previousKey;
                Integer nextKey;
                if (entryKey > 0) {
                    previousKey = entryKey - 1;
                    String previousTuple = divisionMap.get(previousKey);
                    String previousTupleReplacement = previousTuple.substring(0, 2) + result;
                    divisionMap.put(previousKey, previousTupleReplacement);
                }
                if (entryKey < divisionMap.size() - 1) {
                    nextKey = entryKey + 1;
                    String nextTuple = divisionMap.get(nextKey);
                    String nextTupleReplacement = result + nextTuple.substring(1) ;
                    divisionMap.put(nextKey, nextTupleReplacement);
                }
            }
        }
        return divisionMap;
    }

    public static double div(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return num1 / num2;
    }
}
//    public static double add(double num1, double num2) {
//        return num1 + num2;
//
//    }
//
//    public static double sub(double num1, double num2) {
//        return num1 - num2;
//
//    }
//
//    public static double mul(double num1, double num2) {
//        return num1 * num2;
//    }
//
//





