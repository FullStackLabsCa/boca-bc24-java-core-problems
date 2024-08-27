package problems.array_problems.removevowels;

import java.util.Scanner;


public class RemoveVowels {
    public static void removeVowels(String input) {
        input = input.toLowerCase();
        String output="";
        int stringLength = input.length();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                continue;
            }
            else{
                System.out.print(ch);
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String inputString = input.nextLine();
        removeVowels(inputString);
    }
}
