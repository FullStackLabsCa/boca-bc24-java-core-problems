package problems.string.advanced.calculator;

import java.util.*;

import static java.lang.Double.parseDouble;

@SuppressWarnings("java:S106")
public class Calculator {
    public static void calculate(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Error: Input is empty or null");
            return;
        }

        LinkedHashMap<Integer, String> checkBracketsMap = new LinkedHashMap<>();
        String[] parts;
        String tuple = "";
        parts = str.split(" ");
        int index = 0;

        //making tuples and adding to the Map
        for (int i = 0; i < parts.length - 1; i = i + 2) {
            //checking validation if it passes then adding to the Map
            if (parts[i].equals(" ") || parts[i + 2].equals(" ")) {
                System.out.println("Error: Invalid input format");
            } else {
                tuple = parts[i] + parts[i + 1] + parts[i + 2];
                checkBracketsMap.put(index, tuple);
                index++;
            }
        }

        for (Map.Entry<Integer, String> entry : checkBracketsMap.entrySet()) {
            Number k = entry.getKey();
            String v = entry.getValue();
            int previousNodeKey = (int) k - 1;
            int nextNodeKey = (int) k + 1;
            double num1 = 0;
            double num2 = 0;
            double operandResult = 0;
            String value = v.toString();
            char[] array = value.toCharArray();
            String operand = "";
            boolean containOpenCloseBracket = false;

            if (array.length == 5 && array[0] == '(' && array[4] == ')') {
                operand = String.valueOf(array[2]);
                containOpenCloseBracket = true;
                num1 = parseDouble(String.valueOf(array[1]));
                num2 = parseDouble(String.valueOf(array[3]));
            }
            //previousNode logic
            if (containOpenCloseBracket) {
                operandResult = performOperation(num1, operand, num2);
                if (previousNodeKey != -1) {
                    String previousNode = checkBracketsMap.get(previousNodeKey);
                    String valueSplit = "";
                    String[] splitArray = previousNode.split("");
                    for (int i = 0; i < splitArray.length; i++) {
                        if (splitArray[i].contains("(") && splitArray[0] == "(") {
                            valueSplit = splitArray[1] + splitArray[2] + operandResult;
                            break;
                        } else if (splitArray[i].contains("(") && splitArray[0] != "(") {
                            valueSplit = splitArray[0] + splitArray[1] + operandResult;
                            break;
                        }
                    }
                    checkBracketsMap.put(previousNodeKey, valueSplit);
                }
                //nextNode Logic
                if (nextNodeKey < checkBracketsMap.size()) {
                    String nextNode = checkBracketsMap.get(nextNodeKey);
                    String valueSplit = "";
                    String[] splitArray = nextNode.split("");
                    for (int i = 0; i < splitArray.length; i++) {
                        if (splitArray[i].contains(")") && splitArray[splitArray.length - 1] == ")") {
                            valueSplit = splitArray[1] + splitArray[2] + operandResult;
                            break;
                        } else if (splitArray[i].contains(")") && splitArray[1] != ")") {
                            valueSplit = splitArray[0] + splitArray[2] + operandResult;
                            break;
                        }
                    }
                    checkBracketsMap.put(nextNodeKey, valueSplit);
                }

                //removing the processed expression tuple from the Map
                checkBracketsMap.remove(k);
                System.out.println("checkBracketsMap = " + checkBracketsMap);
                break;
            }
        }
//        System.out.println("checkBracketsMap = " + checkBracketsMap);
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

    public static void clearMemory() {
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
