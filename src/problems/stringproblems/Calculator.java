package problems.stringproblems;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    public void calculate(String expression) {
        String operator = " ";
        char charInExpression = 0;
        for (int i = 0; i < expression.length(); i++) {
            charInExpression = expression.charAt(i);
            if (charInExpression == '+' || charInExpression == '-' || charInExpression == '*' || charInExpression == '/') {
                operator = String.valueOf(charInExpression);
                break;
            }
        }
        //making two stacks, one for numbers and other for operators
        Stack numberStack = new Stack<>();
        Stack operatorStack = new Stack<>();


    //putting the priority order for operator in operatorStack -
      operatorStack.push(prioritizeOperator(charInExpression));


    }

    //defining the precedence order
    public int prioritizeOperator(char operators) {
        switch (operators) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;

        }


    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression");
        String expressionFromUser = scanner.nextLine();
        Calculator calculator = new Calculator();


    }
}

