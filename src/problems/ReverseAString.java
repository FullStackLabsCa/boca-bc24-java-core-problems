package problems;

import java.util.Scanner;

public class ReverseAString {
    public static void main(String[] args) {
        System.out.println("Enter the input String");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String revStr = "";
        for (int i = (input.length() - 1); i >= 0; i--) {
            revStr = revStr + input.charAt(i);
        }
        System.out.println(revStr);
    }
}
