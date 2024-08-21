package stringproblem;

import java.util.Scanner;

import static java.lang.System.*;

public class CountVowel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.println("Enter the value: ");
        String input = scanner.nextLine();
        int  vowels = countVowels(input);
        out.println("the count of number of vowels:  "+vowels);


    }

    public static int countVowels(String input) {
        char[] charArray=input.toCharArray();
        char[] vowelsArray= {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int count = 0;

        for (char c : charArray) {
            for (char value : vowelsArray)
                if (c == value) {
                    count++;
                    return count;
                }
        }
        out.println("please Enter the valid Number");
        return 0;
    }
}









