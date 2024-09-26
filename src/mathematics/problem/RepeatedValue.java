package mathematics.problem;

import java.util.Scanner;
public class RepeatedValue {
    public static void main(String[] args) {// duplicate number detecter
        Scanner scanner = new Scanner(System.in); // Read a given string and count how many times a given alphabet or number is repeated
        System.out.println("Enter number to find the duplicate");
        String input = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char charValue = input.charAt(i);
            for (int j = 1 + i; j < input.length(); j++) {
                if (charValue == input.charAt(j)) {
                    System.out.println(charValue);
                    break;
                }
            }
        }
    }
}
