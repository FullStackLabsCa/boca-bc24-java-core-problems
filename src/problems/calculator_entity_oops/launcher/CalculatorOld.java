package problems.calculator_entity_oops.launcher;

import problems.calculator_entity_oops.validation_methods.*;
import java.util.Scanner;

public class CalculatorOld {
    public static void main(String[] args) {
        CalculatorOld calculatorOld = new CalculatorOld();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Input to Calculator....");
        String input = scanner.nextLine();
        calculatorOld.calculate(input);
    }

    public String calculate(String s) {
       return CallAllValidationsAndCalculationLogics.callAllValidation(s);
    }
}