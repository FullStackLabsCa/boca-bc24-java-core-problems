package mathematics.problem;

import java.util.Scanner;
public class RepeatedString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any String");
        String inputString = scanner.nextLine();

        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            for (int j = i + 1; j < inputString.length(); j++) {

                if (currentChar == inputString.charAt(j)) {
                    System.out.println("Repeated word is :" + currentChar);
                    currentChar++;
                }
            }
        }

    }
}
