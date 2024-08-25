package advance_calculator;

import java.util.Scanner;

public class CalculatorLauncher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Calculator\nPlease Enter the values (e.g: 5/2)");
        String input = sc.nextLine();
        String output = new CalculatorUtil().calculate(input);
    }
}
