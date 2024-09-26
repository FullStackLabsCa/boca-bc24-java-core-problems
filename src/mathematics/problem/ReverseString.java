package mathematics.problem;

import java.util.Scanner;
public class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a string to reverse");
        String string = scanner.nextLine();
        System.out.println("String after reversing ");
        for (int i = string.length(); i > 0; --i) {
            System.out.print(string.charAt(i - 1));
        }
    }
}
