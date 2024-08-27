package basic;

import java.util.Scanner;

public class Problem12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = scanner.next();
        System.out.println("Enter a character: ");
        char ch = scanner.next().charAt(0);
        int count = 0;
        System.out.println("My character is:" + ch);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count += 1;
            }
        }
        System.out.println("Count of character '" + ch + "' = " + count);
    }
}
