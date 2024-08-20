package problems;

import Calculator_Entity.Addition;
import Calculator_Entity.Division;
import Calculator_Entity.Multiplication;
import Calculator_Entity.Subtraction;

import java.util.Scanner;

public class Calculator {
    public String calculate(String s) {
        String b = null;
        Scanner scanner = new Scanner(System.in);
        char in = 'f';
        String result = null;
        do {
                FIRST:
                if (s == null || s.trim().isEmpty()) {
                    result = "Error: Input is empty or null";
                } else {
                    String stringchar[];
                    stringchar = s.split("(?=[+\\-*/])|(?<=[+\\-*/])");
                    if (stringchar.length == 3) {
                        for (String a : stringchar) {
//                    System.out.println(a);
                            if (a.isBlank()) {
                                System.out.println(result = "Error: Invalid input format"); //a-zA-Z

                                break FIRST;
                            } else if (a.matches(".*[a-zA-Z].*")) {
                                System.out.println(result = "Error: Invalid number format");
                                break FIRST;
                            }
                        }
                    } else if (stringchar.length != 3) {
                        System.out.println(result = "Error: Invalid format");
                        break FIRST;
                    }
                    float input1 = Float.parseFloat(stringchar[0]);
                    float input2 = Float.parseFloat(stringchar[2]);
                    String operationType = stringchar[1];

                    switch (operationType) {
                        case "+":
                            Addition add = new Addition(input1, input2);
                            System.out.println(result = add.addition());
                            break;
                        case "-":
                            Subtraction sub = new Subtraction(input1, input2);
                            result = sub.subtraction();
                            break;
                        case "*":
                            Multiplication mul = new Multiplication(input1, input2);
                            System.out.println(result = mul.multiplication());
                            break;
                        case "/":
                            Division div = new Division(input1, input2);
                            System.out.println(result = div.division());
                            break;
                        default:
                            System.out.println("Invalid Entry");
                    }
                    System.out.println("Do you want to continue or Exit\n" +
                            "To exit please press x or press any other character key");
                    in = scanner.next().charAt(0);
                    System.out.println(scanner.nextLine());
                    if (in != 'x') {
                        System.out.println("Please Enter the values (e.g: 5/2)");
//                    String b = null;
                        b = scanner.nextLine();
                        s = b;
                    }
                }
        } while (in != 'x');
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Calculator\nPlease Enter the values (e.g: 5/2)");
        String input = sc.nextLine();
//        while(true) {
        new Calculator().calculate(input);

//        }
    }
}
