package calculator;


import java.util.Scanner;

public class Calculator {
    public String calculate(String s) {

        if (s == null || s.isEmpty()) {
            return ("Error: Input is empty or null");
        }

        String[] parts = s.split("(?<=[-+*/])|(?=[-+*/])");
        if(parts[0].equals("abc "))
        {
            return ("Error: Invalid number format");
        }

       if(parts[2].equals(" ")) {
        return ("Error: Invalid input format");
        }

            double number1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double number2 = Double.parseDouble(parts[2]);

        //System.out.println("Error: Invalid number format");
           if (number2 == 0)
            return ("Error: Cannot divide by zero");
        String result = "";

       // System.out.println("The string is empty or null, cannot parse to a number.");
            switch (operator) {
                case "+":
                    Addition add = new Addition(number1, number2);
                    result = add.getResult();
                    break;
                case "-":
                    Substraction sub = new Substraction(number1, number2);
                     result= sub.getResult();
                    break;
                case "*":
                    Multiplication multi = new Multiplication(number1, number2);
                    result=multi.getResult();
                    break;
                case "/":
                    Divide divide = new Divide(number1, number2);
                    result=divide.getResult();
                    break;
                default:
                    return("Error: Input is empty or null");

            }
            return result;
    }

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the value");
        String s = scanner.nextLine();
        char var;
        do {
            Calculator calculator = new Calculator();
            System.out.println(calculator.calculate(s));
            System.out.println("to exit please enter x");
             var = scanner.next().charAt(0);

        }while (var!='x');

    }

}






