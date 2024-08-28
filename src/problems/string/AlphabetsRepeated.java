package problems.string;

import java.util.Scanner;

public class AlphabetsRepeated {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String word = scanner.nextLine().toLowerCase();

        System.out.println("Enter a letter you need to know how many times it occurred: ");
        char c = scanner.nextLine().charAt(0);

        int count = 0;
        int len = word.length();

        for (int i = 0; i < len; i++) {
            if (word.charAt(i) == c) {
                count++;
            }
        }

        System.out.println("The letter '" + c + "' occurred " + count + " times.");
    }
}
