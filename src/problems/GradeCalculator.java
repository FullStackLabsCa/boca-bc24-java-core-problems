package problems;

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int studentInputDoubleDigit = input.nextInt();

        int studentInputSingleDigit = studentInputDoubleDigit / 10;


        switch (studentInputSingleDigit) {
            case 9:
                System.out.println("A");
                break;
            case 8:
                System.out.println("B");
                break;
            case 7:
                System.out.println("C");
                break;
            case 6:
                System.out.println("D");
                break;
            default:
                System.out.println("F");
                break;
        }
    }


}
