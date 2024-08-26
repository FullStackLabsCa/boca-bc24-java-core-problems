package problems.oldProblems.IndirectSubtractNums;

import java.util.Scanner;

public class IndirectSubtractNums {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter two numbers to subtract: ");
        int a = s.nextInt();
        int b = s.nextInt();

        while(b!=0){
            a--;
            b--;
        }

        System.out.println("Subtraction of two numbers without directly subtracting them is: " + a);
    }
}
