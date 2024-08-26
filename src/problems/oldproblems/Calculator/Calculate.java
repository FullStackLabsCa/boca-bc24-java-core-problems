package problems.oldproblems.Calculator;

import Calculator.Arithematic.Addition;
import Calculator.Arithematic.Division;
import Calculator.Arithematic.Multiplication;
import Calculator.Arithematic.Subtraction;

import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String str = "";
        do {
            System.out.println("Enter operation to perform calculation: ");
            str = s.nextLine();

            if(str.matches("\\d+[.]?\\d+[+\\-*/]\\d+[.]?\\d+$")){
                if(str.contains("+")){
                    String[] st = str.split("\\+");
                    Addition add = new Addition(st[0], st[1]);
                    System.out.println("Addition is: " + add.add());
                } else if(str.contains("-")){
                    String[] st = str.split("-");
                    Subtraction sub = new Subtraction(st[0], st[1]);
                    System.out.println("Subtraction is: " + sub.subtract());
                } else if(str.contains("*")){
                    String[] st = str.split("\\*");
                    Multiplication mul = new Multiplication(st[0], st[1]);
                    System.out.println("Multiplication is: " + mul.multiply());
                } else{
                    String[] st = str.split("/");
                    if(!st[1].contains("0")) {
                        Division div = new Division(st[0], st[1]);
                        System.out.println("Division is: " + div.divide());
                    } else{
                        System.out.println("Divide by zero error, please try again.");
                    }
                }
            } else if(!str.equalsIgnoreCase("x")){
                System.out.println("Invalid input, please try again.");
            }
        } while(!str.equalsIgnoreCase("x"));
    }
}
