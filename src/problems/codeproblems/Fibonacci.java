package problems.codeproblems;

import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static List generateFibonacci(int number) {

        int numberOne = 0;
        int numberTwo = 1;
        for (int i = 1; i <= number; i++) {
            System.out.println(numberOne + " ");
            int numberThree = numberOne + numberTwo;
            numberOne = numberTwo;
            numberTwo = numberThree;
        }

        return List.of();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter value");
        int userNumber = 0;
        
        if(scanner.nextLine() == " ") {

            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
            
            if((userNumber < 4) || (userNumber >= 47) ){
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            }


        if (userNumber > 4 && userNumber <= 47) {
            Fibonacci fibonacci = new Fibonacci();
            fibonacci.generateFibonacci(userNumber);
        }
        else System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");

        }
    }



