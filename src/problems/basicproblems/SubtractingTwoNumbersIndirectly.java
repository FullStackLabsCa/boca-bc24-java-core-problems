//Add two numbers without directly adding them

import java.util.Scanner;

public class SubtractingTwoNumbersIndirectly {
    public static void main(String[] args) {


        int subtraction = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter two number to subtract");
        String[] inputNumbers = scanner.nextLine().split(" ");
        subtraction = Integer.parseInt(inputNumbers[0]) - Integer.parseInt(inputNumbers[1]);

        System.out.println("sum is " + subtraction);
    }
}
