package loopproblems;

import java.util.Scanner;

public class CountAlphabet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String string = scanner.nextLine();
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            for (int j = i + 1; j < string.length(); j++) {
                if (ch == string.charAt(j)) {
                    System.out.println("Repeated string is: " + ch);
                    break;
                }
            }
        }
    }
}
