package advance_calculator;

import java.util.Arrays;
import java.util.Scanner;

public class CalculatorLauncher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        char exitInput;
        System.out.println("Welcome to Calculator");
//        Calculator calculator = new Calculator();
        do {
            System.out.println("Please Enter the values (e.g: 5/2)");
            input = sc.nextLine();
            switch (input){
                case "recallMemory()":
                    System.out.println(Calculator.recallMemory());
                    break;
                case "clearMemory()":
                    Calculator.clearMemory();
                    break;
                case "recallAllMemory()":
                    Calculator.recallAllMemory();
                    break;
                default:
                   Calculator.calculate(input);
                    break;
            }
            System.out.println("To exit please press 'X' otherwise press any key");
            exitInput = sc.nextLine().charAt(0);
        }while ('X'!=exitInput);
    }
}
