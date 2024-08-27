package basic;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = scanner.nextLine();

        if (str.isEmpty()) {
            System.out.println("Empty String");
            return;
        }

        if (str.length() == 1) {
            System.out.println("Enter a string more than 1 charter");
        }

        int i = str.length() - 1;
        StringBuilder reverseString = new StringBuilder();
        while (i >= 0) {
            reverseString.append(str.charAt(i));
            i -= 1;
        }
        System.out.println("Reverse string is: " + reverseString);
    }
}
