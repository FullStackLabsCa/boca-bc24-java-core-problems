package problems.codeproblems;

import java.util.Scanner;

public class ReverseOfString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string");
        String userValue = scanner.nextLine();
        String stringReverse = "";
        for(int i = userValue.length() - 1; i >= 0; i--) {
            stringReverse = stringReverse + userValue.charAt(i);
        }
        System.out.println("Reverse of the string is: " + stringReverse);
    }
}
