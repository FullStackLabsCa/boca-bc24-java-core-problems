package problems;

import java.util.Scanner;

public class SimpleCalculator {

    public String add(int firstNum, int secondNum){
        return String.valueOf((double)firstNum + secondNum);
    }

    public String subtract(int firstNum, int secondNum){
        return String.valueOf((double)firstNum - secondNum);
    }

    public String multiply(int firstNum, int secondNum){
        return  String.valueOf((double)firstNum*secondNum);
    }

    public static String validateInput(String input){
        boolean hasAlphabet = false, hasOperator = false;
        String result = null;
        int operatorCount = 0;

        //check if string has alphabets
        for(int i = 0; i < input.length(); i++){
            if(input.matches(".*[a-zA-Z]+.*")){
                hasAlphabet = true;
                return "Error: Invalid number format";
            }
        }
        //check if string has multiple operators or just one
        for(int i = 0; i < input.length(); i++){
            if(String.valueOf(input.charAt(i)).matches("[-+*/]")){
                hasOperator = true;
                operatorCount++;
            }
        }

        if(operatorCount>1){
            return "Incorrect Input!!! You entered multiple operators. Please process one at a time.";
        }
        else {
            result = "Valid";
        }

        return result;
    }

    public String division(int numerator, int denominator){
        if(denominator == 0){
            return "Error: Cannot divide by zero";
        }else {
            return String.valueOf(((double) numerator)/denominator);
        }
    }

    public static String calculate(String input){
        SimpleCalculator testCalc = new SimpleCalculator();

        if(input == null || input.trim().isEmpty()){
            return "Error: Input is empty or null";
        }

        //Remove spaces from user's Input
        input = input.replace(" ", "");

        //Validate user input for Valid operations and operands
        if(SimpleCalculator.validateInput(input) != "Valid"){
            return SimpleCalculator.validateInput(input);
        }

        //Extract the operands
        String[] operands = input.split("(?=[+*/-])|(?<=[+*/-])");

        if(operands.length != 3){
            return "Error: Invalid input format";
        }

        //Figure out the operation being processed
        String calcOperator  = operands[1];

        //Perform operation based on operator
        String result = "";
        switch (calcOperator){
            case "+":
                result = testCalc.add(Integer.parseInt(operands[0]), Integer.parseInt(operands[2]));
                break;
            case "-":
                result = testCalc.subtract(Integer.parseInt(operands[0]), Integer.parseInt(operands[2]));
                break;
            case "*":
                result = testCalc.multiply(Integer.parseInt(operands[0]), Integer.parseInt(operands[2]));
                break;
            case "/":
                result = testCalc.division(Integer.parseInt(operands[0]), Integer.parseInt(operands[2]));
                break;
            default:
                break;
        }

        return result;
    }


    public static void main(String[] args) {

        int[] copy = new int[5];

        //Take user Input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Arithmetic Operation: ");
        String input = scanner.nextLine();

        System.out.println(calculate(input));

    }
}
