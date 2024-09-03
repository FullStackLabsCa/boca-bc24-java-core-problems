package problems.oldcalculator;

import problems.calculator.ArithmeticOperations;

import java.util.*;

public class CalculatorG {

    public static String str = "5 + (6 * (2 - 4) + 3) - 1";
    public static List<Double> storedMemoryList= new ArrayList<>();

    public static Double removeListElement(int position, List<String> listString, Double answerPart) {
        if(listString.stream().anyMatch(s -> s.startsWith("sqrt("))){
            for (int i = 0; i < listString.size(); i++) {
                if (listString.get(i).startsWith("sqrt(")) {
                    listString.remove(i);
                }
            }
            listString.add(position , answerPart.toString());
            System.out.println(listString.toString());

        }
        else {
            for (int i = 0; i < 3; i++) {
                System.out.println(listString.toString());
                listString.remove(position - 1);
                System.out.println(listString.toString());
            }

            listString.add(position - 1, answerPart.toString());
            System.out.println(listString.toString());
        }
        return Double.parseDouble(listString.get(0));
    }

    public static int operatorPosition(List<String> listString, String operator) {
        for (int i = 0; i < listString.toArray().length; i++) {
            if (Objects.equals(listString.get(i), operator)) {
                return i;
            }
        }
        return 0;
    }

    public static void storeInMemory(double value){
        if (storedMemoryList.size()<5){
            storedMemoryList.add(value);
            System.out.println("Added "+value);
        }
        else{
            System.out.println("Removing: "+storedMemoryList.get(0));
            storedMemoryList.remove(0);

            storedMemoryList.add(value);
            System.out.println("Added "+value);
        }

    }

    public static Double recallMemory(){
        if (storedMemoryList.get(storedMemoryList.size()-1)==null){
            System.out.println("No values stored in memory.");
        }
        return storedMemoryList.get(storedMemoryList.size()-1);
    }

    public static void clearMemory(){
        System.out.println("No values stored in memory.");
        storedMemoryList.clear();
    }

    public static Object recallAllMemory(){
        if (storedMemoryList.isEmpty()) {
            System.out.println("No values stored in memory.");
            return "No values stored in memory.";
        }
        System.out.print("Stored values: "+storedMemoryList.toString().replace("[", "").replace("]", ""));

        return "Stored values: "+storedMemoryList.toString().replace("[", "").replace("]", "");

    }

    public static double calculate(String str) {

        String newStr = str.trim();
        String[] parts = newStr.split(" ");
        System.out.println("to = " + Arrays.toString(parts));
        List<String> listString = Arrays.asList(parts);
        listString = new ArrayList<>(listString);


        int startPosition = startPriorityOperator(listString);
        double partAnswer=0;
        int ctr;
        if (startPosition >=0) {
            while (startPosition >= 0) {
                int endPosition = endPriorityOperator(listString);

                List<String> slicedStringList = listString.subList(startPosition + 1, endPosition);
                System.out.println("slicedStringList = " + slicedStringList);
                int tempStartPosition = startPosition;
                int tempEndPosition = listString.toArray().length - endPosition;
                startPosition = startPriorityOperator(slicedStringList);
                ctr=1;
                while (startPosition > 0) {
                    endPosition = endPriorityOperator(slicedStringList);
                    tempStartPosition = tempStartPosition + startPosition + 1;
                    tempEndPosition = endPosition - startPosition;
                    slicedStringList = slicedStringList.subList(startPosition + 1, endPosition);
                    startPosition = startPriorityOperator(slicedStringList);
                    ctr--;
                }
                System.out.println("slicedStringList" + slicedStringList + "start position" + tempStartPosition + "tempEndPosition" + tempEndPosition);
                if(ctr<1) {

                    removeBrackets(slicedStringList, listString, tempStartPosition, tempEndPosition);
                    partAnswer = arithmeticOperation(slicedStringList);
                    removeParanthrisAnswer(tempStartPosition, tempStartPosition + tempEndPosition, listString, partAnswer);
                }
                else{
                    removeBrackets(slicedStringList, listString, startPriorityOperator(listString), slicedStringList.size()+1);
                    partAnswer = arithmeticOperation(slicedStringList);
                    removeParanthrisAnswer(tempStartPosition, endPriorityOperator(listString) , listString, partAnswer);
                }
                startPosition = startPriorityOperator(listString);

            }

        }
        else {
            return arithmeticOperation(listString);
        }

        return arithmeticOperation(listString);

    }

    private static void removeBrackets(List<String> slicedStringList, List<String> listString, int tempStartPosition, int endPosition) {
        slicedStringList.add(listString.get(tempStartPosition + endPosition ).replace(")", ""));
        slicedStringList.add(0, listString.get(tempStartPosition).replace("(", ""));

        System.out.println("slicedStringList = " + slicedStringList);
    }

    private static void removeParanthrisAnswer(int startPosition, int endPosition, List<String> listString, double partAnswer) {
        System.out.println("listString = " + listString);
        for (int i = startPosition; i <= endPosition; i++) {

            listString.remove(startPosition);
            System.out.println("listString = " + listString);
        }

        listString.add(startPosition, (String.valueOf(partAnswer)));
        System.out.println("listString = " + listString);
    }


    private static int startPriorityOperator(List<String> listString) {
        for (int i = 0; i < listString.toArray().length; i++) {
            if (listString.get(i).startsWith("(")) {
                return i;
            }
        }
        return -1;
    }

    private static int endPriorityOperator(List<String> listString) {
        for (int j = listString.toArray().length - 1; j > 0; j--) {
            System.out.println(listString.get(j));
            if (listString.get(j).endsWith(")")) {

                return j;
            }
        }
        return -1;
    }

    private static Double arithmeticOperation(List<String> listString) {

        double answerPart=0;
        while (listString.stream().anyMatch(s -> s.startsWith("sqrt("))) {
            int position = 0;
            String tempString = "";
            String calculateSqrt = null;
            for (int i = 0; i < listString.size(); i++) {
                if (listString.get(i).startsWith("sqrt(")) {
                    tempString = listString.get(i);
                    int startPosition = 0;
                    int endPosition = 0;
                    for (int j = 0; j < tempString.length(); j++) {
                        System.out.println("j = " + j + " " + tempString.toCharArray()[j]);

                        if (Objects.equals(tempString.toCharArray()[j], ')')) {
                            endPosition = j;
                        }

                        if (Objects.equals(tempString.toCharArray()[j], '(')) {
                            startPosition = j;
                        }

                    }
                    for (int k = startPosition; k <= endPosition; k++) {
                        calculateSqrt = new String(tempString.toCharArray(), startPosition+1, endPosition - startPosition-1);

                    }
                }
            }
            answerPart = problems.calculator.ArithmeticOperations.sqrt(( Double.parseDouble(calculateSqrt)));

            answerPart = removeListElement(position, listString, answerPart);
        }

        while (listString.contains("^")) {

            int position = operatorPosition(listString, "^");
            answerPart = problems.calculator.ArithmeticOperations.power(Double.parseDouble(listString.get(position - 1)), Double.parseDouble(listString.get(position + 1)));

            answerPart= removeListElement(position, listString, answerPart);
        }


        while (listString.contains("/")) {

            int position = operatorPosition(listString, "/");
            checkInvalidInput(listString.get(position - 1));
            checkInvalidInput(listString.get(position + 1));
            answerPart = problems.calculator.ArithmeticOperations.division(Double.parseDouble(listString.get(position - 1)), Double.parseDouble(listString.get(position + 1)));

            answerPart= removeListElement(position, listString, answerPart);

        }
//            System.out.printf(listString.toString());
        while (listString.contains("*")) {

            int position = operatorPosition(listString, "*");

            checkInvalidInput(listString.get(position - 1));
            checkInvalidInput(listString.get(position + 1));

            answerPart = problems.calculator.ArithmeticOperations.multiplication(Double.parseDouble(listString.get(position - 1)), Double.parseDouble(listString.get(position + 1)));

            answerPart= removeListElement(position, listString, answerPart);
        }
        while (listString.contains("+")) {

            int position = operatorPosition(listString, "+");
            checkInvalidInput(listString.get(position - 1));
            checkInvalidInput(listString.get(position + 1));
            answerPart = problems.calculator.ArithmeticOperations.addition(Double.parseDouble(listString.get(position - 1)), Double.parseDouble(listString.get(position + 1)));

            answerPart= removeListElement(position, listString, answerPart);
        }
        while (listString.contains("-")) {

            int position = operatorPosition(listString, "-");
            checkInvalidInput(listString.get(position - 1));
            checkInvalidInput(listString.get(position + 1));
            answerPart = ArithmeticOperations.subtraction(Double.parseDouble(listString.get(position - 1)), Double.parseDouble(listString.get(position + 1)));

            answerPart= removeListElement(position, listString, answerPart);
        }
        while (listString.contains("M+")) {


            storeInMemory(answerPart);
            for (int i = 0; i < listString.size(); i++) {
                if (listString.get(i).startsWith("M+")) {
                    listString.remove(i);
                }
            }

        }


        return answerPart;

    }

    private static void checkInvalidInput(String currentValue) {
        if (currentValue.contains("+") || currentValue.contains("-") || currentValue.contains("/") || currentValue.contains("*") || currentValue.contains("^")) {
            System.out.println("Invalid expression.");
        }
    }


    public static void main(String[] args) {

        System.out.println("answer = " + calculate("sqrt(9) - 2 ^ 3 M+"));
        System.out.println("Recall one Value :"+recallMemory());
        recallAllMemory();
        clearMemory();
//        recallMemory();


    }
}