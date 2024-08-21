package problems.string;

import java.sql.Array;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CapitalFirstLetter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = scanner.nextLine().toLowerCase();

        capitalizeWords(s);
    }

    public static void capitalizeWords(String input) {
        String s;
        for(int i=0; i<input.length(); i++) {
            System.out.println(Character.toUpperCase(input.charAt(0)) + input.substring(1));
        }
        }
}
