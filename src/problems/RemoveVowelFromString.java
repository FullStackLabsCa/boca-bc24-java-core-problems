package problems;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveVowelFromString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please Enter Input String (Enter x/X to exit program): ");
            String input = scanner.nextLine();
            if (input.trim().toLowerCase().matches("x")) return;
           // System.out.println("String after removing vowels\t:\t" + removeVowels(input));
            System.out.println("String after removing vowels\t:\t" + removeVowels2(input));
        } while (true);
    }

    static String removeVowels(String input) {
        return input.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .filter(c -> c.toString().matches("[^aeiouAEIOU]"))
                .collect(Collectors.joining());

    }

    static String removeVowels2(String input) {
        return input.replaceAll("[aeiouAEIOU]", "");

    }
}
