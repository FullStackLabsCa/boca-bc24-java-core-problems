package problems;

import problems.calculator_entity.validation_methods.*;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Input to Calculator....");
        String input = scanner.nextLine();
        calculator.calculate(input);
    }

    public String calculate(String s) {
       return CallAllValidationsAndCalculationLogics.callAllValidation(s);
    }
}