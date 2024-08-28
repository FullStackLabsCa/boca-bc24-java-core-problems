package problems;

import java.util.Scanner;

public class SubtractNumsWithoutSubtracting {
    public static void main(String[] args) {
        Scanner inputNum = new Scanner(System.in);
        System.out.println("Enter numOne : ");
        int numOne = inputNum.nextInt();
        System.out.println("Enter numTwo : ");
        int numTwo = inputNum.nextInt();


        int total = numOne;

        for (int i = 0; i < numTwo; i++) {
            total--;

        }
        System.out.println("Sum of numOne and numTwo : " + total);
    }
}


