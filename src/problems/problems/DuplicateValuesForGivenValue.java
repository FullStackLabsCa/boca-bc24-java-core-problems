package problems.problems;

import java.util.Scanner;

public class DuplicateValuesForGivenValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string for the count:");
        String string = scanner.nextLine();
        System.out.println("Enter the character to count:");
        char check = scanner.nextLine().charAt(0);
        int ctr = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == check) {
                ctr++;
                stringBuilder.append(check);
            }
        }
        System.out.println("Matched " + ctr + " times");
    }
}
