package strings;

import java.util.Scanner;

public class CapFirstWord {
    public static void main(String[] args) {
        String[] input = getInput().split(" ");
        capFirstWord(input);
    }

    private static void capFirstWord(String[] input) {
        StringBuilder output = new StringBuilder();
        for (String s : input) {
           output.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
        }
        System.out.println(output);
    }

    private static String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        return sc.nextLine();

    }
}
