package mathematicsproblem;

import java.util.Scanner;
public class PallandromeOrNot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a string to reverse");
        String string = scanner.nextLine();
        String reverse = "";
        for (int i = string.length(); i > 0; --i) {
            char ch = string.charAt(i - 1);
            reverse = ch + reverse;
        }
        if (reverse == string) {
            System.out.println("yes");
        } else {
            System.out.println("No");
        }
        System.out.println("reverse string: " + reverse);
        System.out.println("actual string: " + string);
    }
}
