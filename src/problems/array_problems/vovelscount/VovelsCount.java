package problems.array_problems.vovelscount;

import java.util.Scanner;

public class VovelsCount {
    private static String inputString;
    private static int vowelCount;

    public static void countVowels(String input) {
        if (input == null) {
            System.out.println("0");
        } else {
            vowelCount = 0;
            String newinput = input.toLowerCase();
            for (int i = 0; i < newinput.length(); i++) {
                char ch = newinput.charAt(i);
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {



                    vowelCount++;
                }

            }
            System.out.println(vowelCount);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string :- ");
        String inputString = input.nextLine();
        countVowels(inputString);
    }
}