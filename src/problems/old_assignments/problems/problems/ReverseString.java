package problems.old_assignments.problems.problems;

import java.util.Scanner;

public class ReverseString {
    //10 Reverse Abc string without using the inbuilt functions provided by the language

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String input = scanner.nextLine();
        char[] charArray = input.toCharArray();

        for (int i = charArray.length-1; i >= 0; i--) {
            System.out.print(charArray[i]);
        }
        System.out.println(" ");
        System.out.println("***********************");
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString("naraK");
    }

    public void reverseString(String input){
        char[] charArray = input.toCharArray();
        for (int i = charArray.length-1; i >= 0; i--) {
            System.out.print(charArray[i]);
        }
    }

}
