//Reverse a string without using the inbuilt functions provided by the language

import java.util.Scanner;

public class ReverseString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string to reverse");
        String string = scanner.nextLine();
        StringBuilder reverseString = new StringBuilder();
        int counter = 1;
        for (int i = 0; i <string.length() ; i++) {
            reverseString.append(string.charAt(string.length() - counter));
            counter++;
        }

        System.out.println(reverseString);

    }

}
