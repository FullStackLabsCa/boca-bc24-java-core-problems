package advance_calculator;

import java.util.Arrays;

public class Calculator {
    static String operationType;
    static int count = 0;
    static String result = null;
    static int openBrcIndex = 0, closeBrcIndex = 0;
    static String[] memoryArray = new String[5];
    static int memoryStored=0;

    // Method to perform Arithmetic Operation
    public static String operation(String operationType, double input1, double input2) {
        switch (operationType) {
            case "+":
                result = String.valueOf(new Addition(input1, input2).addition());
                count++;
                break;
            case "-":
                result = String.valueOf(new Subtraction(input1, input2).subtraction());
                count++;
                break;
            case "*":
                result = String.valueOf(new Multiplication(input1, input2).multiplication());
                count++;
                break;
            case "/":
                result = String.valueOf(new Division(input1, input2).division());
                count++;
                break;
            case "^":
                result = String.valueOf(new Exponential(input1, input2).exponential());
                count++;
                break;
            case "sqrt":
                result = String.valueOf(new SquareRoot(input1, input2).squareRoot());
                count++;
                break;
            default:
                System.out.println("Invalid Entry");
        }
        return result;
    }

    //Method to check the index of a null element in an Array
    public static int nullIndex(String[] array) {
        int nullAt = 0;
        for (int nullcheck = 0; nullcheck < array.length; nullcheck++) {
            if (array[nullcheck] == null) {
                nullAt = nullcheck;
                break;
            }
        }
        return nullAt;
    }

    // Method to Shift the elements to remove null element
    public static String[] shiftedArray(String[] getarray) {
        int index = nullIndex(getarray);
        String[] shiftedTemp = new String[getarray.length + 1];
        System.arraycopy(getarray, 0, shiftedTemp, 0, getarray.length);
        for (int i = (index + 1); i < getarray.length; i++) {
            if (getarray[i] == null) {
                System.arraycopy(getarray, i, getarray, i - 1, getarray.length - i);
                break;
            }
            if (getarray[i].matches(".*[+\\-*^].*")) {
                for (int j = index; j < getarray.length; j++) {
                    getarray[j] = shiftedTemp[i];
                    if (i == getarray.length || getarray[i] == null) break;
                    else i++;
                }
                --i;
            }
        }
        return getarray;
    }
    static int j=0;
    public static String[] storeInMemory(double output) {
        for (int i = 0; i < 5; i++) {
            if (memoryArray[i] == null) {
                memoryArray[i] = String.valueOf(output);
                memoryStored++;
                break;
            }
        }
                if (memoryArray[4]!=null) {
                    if (!memoryArray[4].equals(String.valueOf(output))) {
                        memoryArray[j] = String.valueOf(output);
                        j++;
                    }
                }
        return memoryArray;
                }

    // Method to perform expression the bracket first
    public static double calculate(String expression) {
        String[] expressionIntoArray = InputValidator.expressionArray(expression);
        String[] shiftedBrcArray = new String[expressionIntoArray.length];
        String[] brcArray = new String[expressionIntoArray.length];
        double calculatedResult = 0;
        if (InputValidator.expressionChecker(expression)) {
            int index = 0;
            do {
                openBrcIndex = -1;
                closeBrcIndex = 0;
                for (int brc = 0; brc < (expressionIntoArray.length); brc++) {
                    if (expressionIntoArray[brc] == null) break;
                    else {
                        if ((expressionIntoArray[brc].equals("(")) && ((brc == 0) || (!expressionIntoArray[brc - 1].equals("sqrt")))) {
                            openBrcIndex = brc;
                        } else if (expressionIntoArray[brc].equals(")")) {
                            closeBrcIndex = brc;
                            break;
                        }
                    }
                }
                if (openBrcIndex != -1) {
                    System.arraycopy(expressionIntoArray, (openBrcIndex + 1), brcArray, 0, (closeBrcIndex - openBrcIndex - 1));
                    shiftedBrcArray = shiftedArray(brcArray);
                    index = (closeBrcIndex - openBrcIndex - 1);
                    String calculatedBracketExpression = pedmassCal(shiftedBrcArray, index, expression);
                    expressionIntoArray[openBrcIndex] = calculatedBracketExpression;
                    for (int i = closeBrcIndex + 1; i < expressionIntoArray.length; i++) {
                        expressionIntoArray[++openBrcIndex] = expressionIntoArray[i];
                        expressionIntoArray[i] = null;
                    }
                    for (int j = (expressionIntoArray.length - 4); j < expressionIntoArray.length; j++) {
                        expressionIntoArray[j] = null;
                    }

                } else {
                    index = (expressionIntoArray.length);
                    calculatedResult = Double.parseDouble(pedmassCal(expressionIntoArray, index, expression));
                    for (String s : expressionIntoArray) {
                        if (s == null) {
                            continue;
                        } else {
                            if (s.contains("M+")) {
                                storeInMemory(calculatedResult);
                            }
                        }
                    }
                    result = String.valueOf(calculatedResult);
                    System.out.println("Result is : " + result);
                }
            } while (openBrcIndex != -1);
        }
        return  calculatedResult;

    }


    // Method to perform add subtract multiply divide operation in their priority
    public static String pedmassCal(String[] calculationArray, int index, String expression) {
        String[] temp = new String[expression.length() + 1];
        String[] temp2 = new String[expression.length() + 1];
        String[] temp3 = new String[expression.length() + 1];
        String[] temp4 = new String[expression.length() + 1];
        String[] temp5 = new String[expression.length() + 1];

        for (int exp = 0; exp < index; exp++) {
            if (calculationArray[exp] == null) break;
            else {
                if (calculationArray[exp].equals("^")) {
                    operationType = calculationArray[exp];
                    if (calculationArray[exp - 1] == null || calculationArray[exp + 1] == null) {
                        System.out.println("Invalid expression");
                        result = "0.0";
                        break;
                    } else {
                        double input1 = Double.parseDouble(calculationArray[exp - 1]);
                        double input2 = Double.parseDouble(calculationArray[exp + 1]);
                        operation(operationType, input1, input2);
                    }
                }
                if (count > 0) {
                    temp[exp - 1] = result;
                    count = 0;
                } else {
                    temp[exp] = calculationArray[exp];
                }
            }
        }

        String[] shiftedTemp = shiftedArray(temp);

        for (int sqr = 0; sqr < nullIndex(shiftedTemp); sqr++) {
            if (shiftedTemp[sqr] == null) break;
            else {
                if (shiftedTemp[sqr].equals("sqrt")) {
                    operationType = shiftedTemp[sqr];
                    double input2 = 0;
                    if (shiftedTemp[sqr + 2].contains("-")) {
                        throw new ArithmeticException();
                    } else if (shiftedTemp[sqr + 2] == null) {
                        System.out.println("Invalid expression");
                        result = "0.0";
                        break;
                    } else {
                        double input1 = Double.parseDouble(shiftedTemp[sqr + 2]);
                        operation(operationType, input1, input2);
                    }
                }
                if (count > 0) {
                    temp2[sqr] = result;
                    sqr++;
                    count = 0;
                } else {
                    temp2[sqr] = shiftedTemp[sqr];
                }
            }
        }

        String[] shiftedTemp2 = shiftedArray(temp2);

        for (int div = 0; div < nullIndex(shiftedTemp2); div++) {
            if (shiftedTemp2[div] == null) break;
            else {
                if (shiftedTemp2[div].equals("/")) {
                    operationType = shiftedTemp2[div];
                    if (shiftedTemp2[div - 1] == null || shiftedTemp2[div + 1] == null) {
                        System.out.println("Invalid expression");
                        result = "0.0";
                        break;
                    } else {
                        double input1 = Double.parseDouble(shiftedTemp2[div - 1]);
                        double input2 = Double.parseDouble(shiftedTemp2[div + 1]);
                        operation(operationType, input1, input2);
                    }
                }
                if (count > 0) {
                    temp3[div - 1] = result;
                    count = 0;
                } else {
                    temp3[div] = shiftedTemp2[div];
                }
            }
        }

        String[] shiftedTemp3 = shiftedArray(temp3);

        for (int mul = 0; mul < nullIndex(shiftedTemp3); mul++) {
            if (shiftedTemp3[mul] == null) break;
            else {
                if (shiftedTemp3[mul].equals("*")) {
                    operationType = shiftedTemp3[mul];
                    if (shiftedTemp3[mul - 1] == null || shiftedTemp3[mul + 1] == null) {
                        System.out.println("Invalid expression");
                        result = "0.0";
                        break;
                    } else {
                        double input1 = Double.parseDouble(shiftedTemp3[mul - 1]);
                        double input2 = Double.parseDouble(shiftedTemp3[mul + 1]);
                        operation(operationType, input1, input2);
                    }
                }
                if (count > 0) {
                    temp4[mul - 1] = result;
                    count = 0;
                } else {
                    temp4[mul] = shiftedTemp3[mul];
                }
            }
        }

        String[] shiftedTemp4 = shiftedArray(temp4);

        for (int add = 0; add < nullIndex(shiftedTemp4); add++) {
            if (shiftedTemp4[add] == null) break;
            else {
                if (shiftedTemp4[add].equals("+")) {
                    operationType = shiftedTemp4[add];
                    if (shiftedTemp4[add - 1] == null || shiftedTemp4[add + 1] == null) {
                        System.out.println("Invalid expression");
                        result = "0.0";
                        break;
                    } else {
                        double input1 = Double.parseDouble(shiftedTemp4[add - 1]);
                        double input2 = Double.parseDouble(shiftedTemp4[add + 1]);
                        operation(operationType, input1, input2);
                    }
                }
                if (count > 0) {
                    temp5[add - 1] = result;
                    count = 0;
                } else {
                    temp5[add] = shiftedTemp4[add];
                }
            }
        }

        String[] shiftedTemp5 = shiftedArray(temp5);

        FIRST:
        for (int sub = 0; sub < nullIndex(shiftedTemp5); sub++) {
            if (shiftedTemp5[sub] == null) break;
            else {
                if (shiftedTemp5[sub].equals("-")) {
                    operationType = shiftedTemp5[sub];
                    if (shiftedTemp5[sub - 1] == null || shiftedTemp5[sub + 1] == null) {
                        System.out.println("Invalid expression");
                        result = "0.0";
                        break;
                    } else {
                        double input1 = Double.parseDouble(shiftedTemp5[sub - 1]);
                        double input2 = Double.parseDouble(shiftedTemp5[sub + 1]);
                        operation(operationType, input1, input2);
                    }
                }
                if (count > 0) {
                    temp3[sub - 1] = result;
                    count = 0;
                }
            }
        }
        return result;
    }

    public static void clearMemory() {
        for (int i = 0; i < 5; i++) {
            memoryArray[i] = null;
        }
        System.out.println(Arrays.toString(memoryArray));
    }

    public static double recallMemory() {
        double recallMemoryOutput = 0;
       FIRST: for (int i = 4; i >= 0; i--) {
            if (memoryArray[i] != null) {
                recallMemoryOutput = Double.parseDouble(memoryArray[i]);
                return recallMemoryOutput;
            }else if(memoryArray[i]==null){
                continue ;
            }
        }
        return recallMemoryOutput;
    }

    public static String recallAllMemory() {
        boolean isAllElementNull=false;
        for (String element:memoryArray){
            if(element!=null){
                isAllElementNull=false;
            }else isAllElementNull=true;
        }
        if (isAllElementNull){
            return "No values stored in memory.";
        } else {
            StringBuilder recallAllMemoryOutput = new StringBuilder("Stored values: ");
            for (int i = 0; i < memoryArray.length; i++) {
                recallAllMemoryOutput.append(memoryArray[i]);
                if (i < memoryArray.length - 1) {
                    recallAllMemoryOutput.append(", ");
                }
            }
            return recallAllMemoryOutput.toString();
        }
    }
}