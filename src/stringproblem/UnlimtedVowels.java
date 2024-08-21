package stringproblem;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class UnlimtedVowels {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.println("Enter the value: ");
        String input = scanner.nextLine();
        int  vowels = unlimitedVowels(input);
        out.println("the count of number of vowels:  "+vowels);


    }

    public static int unlimitedVowels(String input) {
        char[] charArray=input.toLowerCase().toCharArray();
        char[]vowelsArray= {'a', 'e', 'i', 'o', 'u'};
        int count = 0;

        for (char c : charArray) {
            for (char value : vowelsArray)
                if (c == value) {
                    count++;
                    out.println(value);

                }
        }
        return count;
    }
}
