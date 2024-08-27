//12 Read a given string and count how many times a given alphabet or number is repeated

import java.util.Scanner;

public class ReputationCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string for the repeataion count");
        String string = scanner.nextLine();
        int counter = 0;
        int matchCounter = 0;
        char check;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            check = string.charAt(i);
            counter++;
            for (int j = counter; j < string.length(); j++) {
                if (check == string.charAt(j)) {
                    matchCounter++;
                    stringBuilder.append(check);
                }

            }

        }
        System.out.println("i   " + stringBuilder);
        System.out.println("matched " + matchCounter + "Times");

    }
}
