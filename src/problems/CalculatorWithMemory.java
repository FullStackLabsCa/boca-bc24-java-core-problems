package problems;

import java.util.*;

public class CalculatorWithMemory {
    public static void main(String[] args) {
        //String inputStr = "-2 * 4*4^3*((sqrt(4)/32+3)*3+5)/(6-8)+4*sqrt(8)";
        //String inputStr = "sqrt(4)";
        //String inputStr = "4 * ((4 / 2) / (6-4)) *2";
        //String inputStr = "4";

        Scanner scanner = new Scanner(System.in);
        SizedStack<Double> sizedStack = new SizedStack<>(5);
        String inputStr;
        boolean addToMemory = false;
        while (true) {
            System.out.println("Please Enter Input  (M/m/M+/m+ to add result to Memory / recallMemory / clearMemory / recallAllMemory /  x/X to exit)  =");
            inputStr = scanner.nextLine();
            if (inputStr.toLowerCase().trim().equals("x")) {
                return;
            }
            if (inputStr.contains("recallAllMemory")) {
                if (sizedStack.isEmpty()) {
                    System.out.println("No value stored in memory.");
                    continue;
                } else {
                    System.out.println(sizedStack);
                    continue;
                }
            }

            if (inputStr.contains("recallMemory")) {
                if (sizedStack.isEmpty()) {
                    System.out.println("No value stored in memory.");
                    continue;
                } else {
                    System.out.println(sizedStack.peek());
                    continue;
                }
            }

            if (inputStr.contains("clearMemory")) {
                if (sizedStack.isEmpty()) {
                    System.out.println("No memory value to clear.");
                    continue;
                } else {
                    sizedStack.clear();
                    System.out.println("Memory cleared.");
                    continue;
                }
            } else if (!inputStr
                    .replaceAll("([0-9]|\\.)", "")
                    .replaceAll("sqrt", "")
                    .replaceAll("\\(", "")
                    .replaceAll("\\)", "")
                    .replaceAll("[+*^/-]", "")
                    .replaceAll("[mM+]", "")
                    .trim().isEmpty()) {
                System.out.println(inputStr);
                System.out.println("Invalid input, please enter valid input");
                continue;
            }


            inputStr = inputStr.replaceAll(" ", "").toLowerCase();
            if (inputStr.contains("m+") || inputStr.contains("m")) {
                addToMemory = true;
                inputStr = inputStr.replace("m+", "");
                inputStr = inputStr.replace("m", "");
            }


            int maxParenthese, maxParenthesePosition = 0, maxParenthesePositionEnd = 0, maxParenthesePositionIndex = 0, maxParenthesePositionEndIndex = 0;
            //System.out.println("======== STEP 1: to process all sqrt");
            int tempindex = 0;
            while (inputStr.contains("sqrt")) {
                String tempString = inputStr.substring(inputStr.indexOf("sqrt"), inputStr.indexOf(")", tempindex) + 1);
                String textToSqrt = tempString.substring(tempString.indexOf("(") + 1, tempString.indexOf(")"));
                int numToSqrt = Integer.parseInt(textToSqrt);
                if (numToSqrt < 0) {
                    System.out.println("Square root of a negative number is not allowed.");
                    return;
                }
                //System.out.println("Sqrt of : " + numToSqrt + " is : " + Math.sqrt(numToSqrt));
                //System.out.println("Interim output :" + inputStr.replace(tempString, String.valueOf(Math.sqrt(numToSqrt))));
                inputStr = inputStr.replace(tempString, String.valueOf(Math.sqrt(numToSqrt)));
                tempindex = inputStr.indexOf("sqrt") + 1;
            }
            //System.out.println("======== STEP 2: to process Parentheses from inner most to outer most");

            while (true) {
                List<Map<Integer, Integer>> parenthesePositionList = new ArrayList<>();
                int Parentheseindexcounter = 0, totalParenthese = 0, openParentheseCounter = 0, closeParentheseCounter = 0;
                char tempChar;
                for (int i = 0; i < inputStr.length(); i++) {
                    tempChar = inputStr.charAt(i);
                    switch (tempChar) {
                        case '(':
                            openParentheseCounter++;
                            totalParenthese++;
                            parenthesePositionList.add(Map.of(i, totalParenthese));
                            Parentheseindexcounter++;
                            break;
                        case ')':
                            closeParentheseCounter++;
                            totalParenthese--;
                            parenthesePositionList.add(Map.of(i + 1, totalParenthese));
                            Parentheseindexcounter++;
                            break;
                    }
                }

//      find max value in the parenthese array
                maxParenthese = Integer.MIN_VALUE;
                for (int i = 0; i < parenthesePositionList.size(); i++) {
                    //System.out.println(parenthesePositionList.get(i));
                    if (parenthesePositionList.get(i).get(parenthesePositionList.get(i).keySet().stream().toList().getFirst()) > maxParenthese) {
                        maxParenthese = parenthesePositionList.get(i).get(parenthesePositionList.get(i).keySet().stream().toList().getFirst());
                        maxParenthesePosition = parenthesePositionList.get(i).keySet().stream().toList().getFirst();
                        maxParenthesePositionIndex = i;
                        maxParenthesePositionEnd = (parenthesePositionList.get(i + 1).keySet().stream().toList().getFirst());
                        maxParenthesePositionEndIndex = i + 1;
                    }
                }

                if (maxParenthese <= 0) {
                    break;
                }
                String tempStringNotFormated = inputStr.substring(maxParenthesePosition, maxParenthesePositionEnd);
                String tempString = tempStringNotFormated.trim().replaceAll(" ", "").replaceAll("[()]", "");
                String result = reduceExpression(tempString);
                if (result == null) {
                    return;
                }
                inputStr = inputStr.replace(tempStringNotFormated, result);
                //System.out.println("Interim output :" + inputStr);
                parenthesePositionList.remove(maxParenthesePositionIndex);
                parenthesePositionList.remove(maxParenthesePositionIndex);
            }
            //To execute the expression without parentheses
            //System.out.println("Result = " + reduceExpression(inputStr));
            System.out.println("Result = " + Math.round(Double.parseDouble(Objects.requireNonNull(reduceExpression(inputStr))) * 100.0) / 100.0);
            if (addToMemory) {
                sizedStack.push(Math.round(Double.parseDouble(Objects.requireNonNull(reduceExpression(inputStr))) * 100.0) / 100.0);
                System.out.println("Result added to Memory.");
                addToMemory = false;
            }
        }

    }


    private static String reduceExpression(String str1) {
        // to calculare remaining expression after all paratheses
        String tempString = str1;
        if (tempString.contains("/0")) {
            System.out.println("Division by 0 is not allowed.");
            return null;
        }
        if (tempString.contains("^-")) {
            System.out.println("Operation not supported.");
            return null;
        }

        //System.out.println("inner most parenthese : " + tempString);
        char[] operators = tempString.replaceAll("[\\d.]", "").toCharArray();
        List<String> expressionList = new ArrayList<>();
        boolean negativeNo = false;
        for (int i = 0; i < operators.length; i++) {
            String tempValue = tempString.substring(0, tempString.indexOf(operators[i]));
            if ((tempValue.trim().isEmpty()) && String.valueOf(operators[i]).equals("-")) {
                negativeNo = true;
            } else {
                if (negativeNo) {
                    expressionList.add("-" + tempValue);
                    negativeNo = false;
                } else {
                    expressionList.add(tempValue);
                }
                expressionList.add(String.valueOf(operators[i]));
            }
            tempString = tempString.substring(tempString.indexOf(operators[i]) + 1);
        }
        if (negativeNo) {
            expressionList.add("-" + tempString);
        } else {
            expressionList.add(tempString);
        }
        while (expressionList.size() > 1) {
            //To process all *, / ,^ in the expression
            while (expressionList.contains("*") || expressionList.contains("/") || expressionList.contains("^")) {

                for (int i = 1; i < expressionList.size(); i++) {
                    String operator = expressionList.get(i);
                    if (operator.equals("*") || operator.equals("/") || operator.equals("^")) {
                        double operand1 = Double.parseDouble(expressionList.get(i - 1));
                        double operand2 = Double.parseDouble(expressionList.get(i + 1));
                        double tempResult = 1;
                        switch (operator) {
                            case "*":
                                tempResult = operand1 * operand2;
                                break;
                            case "/":
                                tempResult = operand1 / operand2;
                                break;
                            case "^":
                                for (int j = 0; j < operand2; j++) {
                                    //tempResult = operand1 * operand1;
                                    tempResult *= operand1;
                                }
                                break;
                        }
                        expressionList.set(i, String.valueOf(tempResult));
                        expressionList.remove(i - 1);
                        expressionList.remove(i);
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
            //to process + , -
            while (expressionList.contains("+") || expressionList.contains("-")) {
                for (int i = 1; i < expressionList.size(); i++) {
                    String operator = expressionList.get(i);
                    if (operator.equals("+") || operator.equals("-")) {
                        double operand1 = Double.parseDouble(expressionList.get(i - 1));
                        double operand2 = Double.parseDouble(expressionList.get(i + 1));
                        double tempResult = 0;
                        switch (operator) {
                            case "+":
                                tempResult = operand1 + operand2;
                                //System.out.println(operand1 + "  " + operator + " " + operand2 + " = " + tempResult);

                                break;
                            case "-":
                                tempResult = operand1 - operand2;
                                //System.out.println(operand1 + "  " + operator + " " + operand2 + " = " + tempResult);
                                break;
                        }
                        expressionList.set(i, String.valueOf(tempResult));
                        expressionList.remove(i - 1);
                        expressionList.remove(i);
                    }
                    i++;
                }
            }
        }
        for (String s : expressionList) {
            str1 = str1.replace(str1, s);
        }
        return str1;
    }
}
