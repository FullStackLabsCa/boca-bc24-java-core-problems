package collections.calculator;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {


    // PSEUDO CODE
    //Process User Input

    //Validate Input
    //No alphabets - Done
    //Operands with 2 '.' are not valid - Done
    //Balanced Parenthesis - Done

    //Get Sub-String for parenthesis (Call it getPriorityCalculations)
    //Make it a list or best do it on the fly
    //Could be a recursive method to evaluate the parenthesis
    //Evaluation based on PEDMAS
    //Parenthesis, Exponent, Division, Multiplication, Addition, Substraction

    //Based on the

    protected static final char[] operatorsAvailable = {'+', '-', '*', '/', '^','$'};

    public static void clearMemory() {
    }

    private static boolean validateInput(String expression) {
        //If the input is Null or Empty String
        if (expression == null || expression.trim().isEmpty()) {
            System.out.println("Illegal Expression!!!");
            return false;
        }
        expression = expression.trim();

        if(!expression.contains("sqrt")) {
            //If the Input has alphabets
            if (expression.matches(".*[a-zA-Z]+.*")) {
                System.out.println("Illegal Arguments!!! Expression Contains Alphabets.");
                return false;
            }
            //Check if the Input have at least 1 operation
            if (getOperators(expression).length == 0) {
                System.out.println("Illegal Expression! Cannot process expressions with no operator.");
                return false;
            }
            //Check for operands with 2 decimals
            for (String operand : getOperands(expression)) {
                int decimalCountInOperand = 0;
                char[] operandChar = operand.toCharArray();
                for (char character : operandChar) {
                    if (character == '.') {
                        decimalCountInOperand++;
                    }
                }
                if (decimalCountInOperand > 1) {
                    System.out.println("Illegal Expression! Cannot process operands with 2 decimals : " + operand);
                    return false;
                }
            }
        }
        //Check if the Parenthesis are balanced
        if (!checkBalancedParenthesis(expression)) {
            System.out.println("Illegal Expression! Parenthesis are not balanced.");
            return false;
        }
        //Other Validations (if Applicable)
        //...
        //Otherwise
        return true;
    }

    private static boolean checkBalancedParenthesis(String expression) {

        Stack<Character> bracketStack = new Stack<>();

        //Execute the logic of checkingBalancedParenthesis
        char[] characters = expression.toCharArray();

        for (char character : characters) {
            if (character == '(' || character == '{' || character == '[') {
                bracketStack.push(character);
            } else if (character == ')' || character == '}' || character == ']') {
                char top = bracketStack.pop();
                if ((top == '(' && character == ')') ||
                        (top == '{' && character == '}') ||
                        (top == '[' && character == ']')) {
                    //NOP
                } else return false;
            }
        }

        if (bracketStack.isEmpty()) {
            return true;
        } else return false;
    }

    private static String[] getOperands(String expression) {
        //Remove spaces from the Expression
        expression = expression.replaceAll(" ", "");
        //Split on operations and return that
        return expression.split("[-+*/^$]");
    }

    private static char[] getOperators(String expression) {
        char[] operatorArrayTemp = new char[expression.length()];
        int operatorCount = 0;
        //Remove spaces from the expression
        expression = expression.replaceAll(" ", "");

        char[] expressionChars = expression.toCharArray();

        //Run the for loop and add the operator to array
        for (char eachChar : expressionChars) {
            for (char operator : operatorsAvailable) {
                if (eachChar == operator) {
                    operatorArrayTemp[operatorCount] = eachChar;
                    operatorCount++;
                    break;
                }
            }
        }
        //Remove the extra elements from the array (could be better optimized using Collections)
        char[] operatorArray = new char[operatorCount];
        System.arraycopy(operatorArrayTemp, 0, operatorArray, 0, operatorCount);

        return operatorArray;
    }

    public static double calculate(String expression) {

        if(expression.contains("sqrt")){
            //return result of sqrt
            String[] Number = expression.split("[\\(\\)]");
            if(Double.valueOf(Number[1]) > 0){
                return Math.sqrt(Double.valueOf(Number[1]));
            } else{
                throw new ArithmeticException();
            }
        } else {
            return processCalculation(expression);
        }
    }


    //Iterate through the string and check if any parenthesis exist in the string:
    //partialExpression = expression
    //while(partialExpression.getOperators != 0)
    //Get the index of opening and closing parenthesis
    //if a new  opening parenthesis is encountered update openingIndex and move toward finding the closing index
    //split the string on the index of opening and closing parenthesis
    //"firstSection" + "sectionUnderProcessing" + "lastSection"
    //sectionUnderProcessing follows EDMAS
    //call method processEDMAS()
    //return type double
    //concatenate partialExpression = firstsection + processEDMAS() + lastSection
    //processCalculation(partialExpression)
    //If no Parenthesis
    //processEDMAS(expression)

    public static double processCalculation(String expression) {
        expression = expression.replaceAll(" ", "");

        if (expression.matches(".*[\\[\\]{}()].*")) { //Expression Contains parenthesis.

            String partialExpression = expression;

            while (getOperators(partialExpression).length != 0) { //This needs some thinking

                int indexOfOpening = 0, indexOfClosing = 0;
                char[] partialExpressionCharacters = partialExpression.toCharArray();

                //Get index of Opening and Closing Parenthesis
                for (int i = 0; i < partialExpressionCharacters.length; i++) {
                    if (partialExpressionCharacters[i] == '(' || partialExpressionCharacters[i] == '{' || partialExpressionCharacters[i] == '[') {
                        indexOfOpening = i;
                    } else if (partialExpressionCharacters[i] == ')' || partialExpressionCharacters[i] == '}' || partialExpressionCharacters[i] == '}') {
                        indexOfClosing = i;
                        break;
                    }
                }

                //Split the string into multiple sections
                String firstSection = partialExpression.substring(0, indexOfOpening);
                String sectionUnderProcessing = partialExpression.substring(indexOfOpening + 1, indexOfClosing);
                String lastSection = partialExpression.substring(indexOfClosing + 1);

                double result = processEDMAS(sectionUnderProcessing);
                partialExpression = String.valueOf(new StringBuilder(firstSection).append(result).append(lastSection));

                processCalculation(partialExpression);
                break;
            }
            return processEDMAS(partialExpression);
        } else {
            return processEDMAS(expression);
        }
    }

    private static double processEDMAS(String sectionUnderProcessing) {
        char[] operators = getOperators(sectionUnderProcessing);
        String[] operands = getOperands(sectionUnderProcessing);

        String resultOfOperation = String.valueOf(performEDMASCalculations(operators, operands));

        return Double.valueOf(resultOfOperation);
    }

    public static double performEDMASCalculations(char[] operators, String[] operands) {
        double result = 0;

//        System.out.println("EDMAS Calculation Method.");
        char[] operations = {'^', '/', '*', '+', '-'};

        char[] newOperators = new char[operators.length - 1];
        String[] newOperands = new String[operands.length - 1];

        if(operators.length == 1) {
            result = Double.valueOf(processOperator(operators[0], 0, operands)[0]);
//            System.out.println("result = " + result);
            return result;
        }

        if (operators.length > 1) {
            for (int i = 0; i < operations.length; i++) {
                for (int j = 0; j < operators.length; j++) {
                    if (operations[i] == operators[j]) {
                        newOperands = processOperator(operations[i], j, operands);
                        for (int k = 0; k < j; k++) {
                            newOperators[k] = operators[k];
                        }
                        for (int k = j; k < operators.length - 1; k++) {
                            newOperators[k] = operators[k + 1];
                        }
                        return performEDMASCalculations(newOperators, newOperands);
                    }
//                    break;
                }
            }
        }

        return result;
    }

    private static String[] processOperator(char operation, int indexOfOperator, String[] operands) {
        double result = 0;
        String[] newOperands = new String[operands.length - 1];

        switch (operation) {
            case '^':
                result = Math.pow(Double.valueOf(operands[indexOfOperator]), Double.valueOf(operands[indexOfOperator + 1]));
                break;
            case '*':
                result = Multiplication.multiply(Double.valueOf(operands[indexOfOperator]), Double.valueOf(operands[indexOfOperator + 1]));
                break;
            case '/':
                result = Division.divide(Double.valueOf(operands[indexOfOperator]), Double.valueOf(operands[indexOfOperator + 1]));
                break;
            case '+':
                result = Addition.add(Double.valueOf(operands[indexOfOperator]), Double.valueOf(operands[indexOfOperator + 1]));
                break;
            case '-':
                result = Substraction.subtract(Double.valueOf(operands[indexOfOperator]), Double.valueOf(operands[indexOfOperator + 1]));
                break;
            default:
                System.out.println("Operation not Recognised");
        }

        operands[indexOfOperator] = String.valueOf(result);
        for (int k = 0; k <= indexOfOperator; k++) {
            newOperands[k] = operands[k];
        }
        for (int k = indexOfOperator + 1; k < operands.length - 1; k++) {
            newOperands[k] = operands[k + 1];
        }

        return newOperands;
    }


    public static double recallMemory() {
        return 0;
    }

    public static void storeInMemory(double v) {
    }

    public static String recallAllMemory() {
        return null;
    }


    public static void main(String[] args) {

        Calculator calculatorV2 = new Calculator();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true){
            System.out.println("Enter an expression: ");
            input = scanner.nextLine();

            if (calculatorV2.validateInput(input)) {
                System.out.println(calculatorV2.calculate(input.trim()));
            }
        }

    }
}
