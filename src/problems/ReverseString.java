package problems;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        System.out.println("Please Enter the String to Reverse it");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        char[] array = string.toCharArray();
        char[] revArray = new char[array.length];
        int i = array.length - 1;
        for (char c : array) {
            if (array.length - 1 > 0) {
                revArray[i] = c;
                i--;
            }
        }
        System.out.println("Reverse String is ");
        for (char a : revArray) {
            System.out.print(a);
        }
    }
}