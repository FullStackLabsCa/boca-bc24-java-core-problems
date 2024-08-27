package problems;

import java.util.Scanner;

public class AddNumsWithoutAdding {
    public static void main(String[] args) {
        Scanner inputNum = new Scanner(System.in);
        System.out.println("Enter numOne : ");
        int numOne = inputNum.nextInt();
        System.out.println("Enter numTwo : ");
        int numTwo = inputNum.nextInt();


        int sum = numOne;

        for (int i = 0 ; i<numTwo;i++){
            sum++;

        }
        System.out.println("Sum of numOne and numTwo : "+ sum);
    }
}
