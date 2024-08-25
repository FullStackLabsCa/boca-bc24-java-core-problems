package advance_calculator;

import java.util.Objects;
import java.util.Scanner;

public class CalculatorUtil {
    public String calculate(String expression) {
        String[] temp = new String[expression.length()];
        String[] temp2 = new String[expression.length()];
        String b = null;
        int count = 0, tempArraySize = 0, temp2ArraySize = 0,tempSize = 0, temp2Size=0;
        Scanner scanner = new Scanner(System.in);
        char in = 'f';
        String result = null;
//        do {
            if (InputValidator.expressionChecker(expression)) {
                String[] expressionIntoArray = InputValidator.expressionArray(expression);
                for (int o = 0; o < expressionIntoArray.length; o++) {
                    if (expressionIntoArray[o].equals("(") || expressionIntoArray[o].equals(")")) {
                        String operationType = expressionIntoArray[o+2];
                        double input1 = Double.parseDouble(expressionIntoArray[o + 1]);
                        double input2 = Double.parseDouble(expressionIntoArray[o + 3]);
                        o=o+4;
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
                            default:
                                System.out.println("Invalid Entry");
                        }
                    }
                    for (int j = o; j <= o; j++) {
                        if (count > 0) {
                            for (int k = 0; k <= j; k++) {
                                if (temp[k] == null) {
                                    temp[k] = result;
                                    break;
                                } else count = 0;
                            }
                        } else if (j == 0 || expressionIntoArray[o].matches("^-?\\d+$") || expressionIntoArray[o].matches("[+\\-*/]")) {
                            for (int k = 0; k <= j; k++) {
                                if (temp[k] == null) {
                                    temp[k] = expressionIntoArray[o];
                                    break;
                                }
                            }
                        }
                    }
                }
                for (String s : temp) {
                    if (s == null) {
                        break;
                    }
                    tempSize++;
                    tempArraySize = tempSize;
                }
                for (int i = 0; i < tempArraySize; i++) {
                    if (temp[i].equals("/") || temp[i].equals("*")) {
                        String operationType = temp[i];
                        double input1 = Double.parseDouble(temp[i-1]);
                        double input2 = Double.parseDouble(temp[i+1]);
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
                            default:
                                System.out.println("Invalid Entry");
                        }
                    }
                    for (int n = i; n <= i; n++) {
                        if (count > 0) {
                            for (int v = 0; v <= n; v++) {
                                if (temp2[v] == null) {
                                    temp2[v] = result;
                                    break;
                                } else count = 0;
                            }
                        } else if (n == 0 || temp[i].matches("^-?\\d+(\\.\\d+)?$") || temp[i].matches("[+\\-*/]")) {
                            for (int v = 0; v <= n; v++) {
                                if (temp2[v] == null) {
                                    temp2[v] = temp[i];
                                    break;
                                }
                            }
                        }
                    }
                }
                for (String s : temp2) {
                    if (s == null) {
                        break;
                    }
                    temp2Size++;
                    temp2ArraySize = temp2Size;
                }
                for (int l = 0; l < temp2ArraySize; l++) {
                    if (temp2[l] == null) {
                        break;
                    } else {
                        if (temp2[l].equals("+") || temp2[l].equals("-")) {
                            String operationType = temp2[l];
                            double input1 = Double.parseDouble(temp2[l - 1]);
                            double input2 = Double.parseDouble(temp2[l + 1]);
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
                                default:
                                    System.out.println("Invalid Entry");
                            }
                            for (int j = l; j <= l; j++) {
                                if (count > 0) {
                                    for (int m = 0; m <= j; m++) {
                                        temp2[0] = result;
                                        l = 0;
                                        System.arraycopy(temp2, l + 3, temp2, l + 1, temp2ArraySize);
//                                        l = 0;
//                                        tempSize=0;
//                                        tempSize=temp.length;
                                        break;
                                    }
                                    count = 0;
                                }
                            }
                        }
                    }

                }
            }
            result = String.valueOf(temp2[0]);
            System.out.println("Result is : " + result);
//            System.out.println();
//            System.out.println("Do you want to continue or Exit\n" +
//                    "To exit please press x or press any other character key");
//            in = scanner.next().charAt(0);
//            System.out.println(scanner.nextLine());
//            if (in != 'x') {
//                System.out.println("Please Enter the values (e.g: 5/2)");
//                b = scanner.nextLine();
//                expression = b;

//            }
//        }
//        while (in != 'x');
        return result;
    }

}
