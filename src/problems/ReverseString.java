package problems;

import java.util.List;
import java.util.Scanner;

public class ReverseString {


    public static void main(String[] args) {

        System.out.println("Input a string:");
        Scanner input = new Scanner(System.in);
        String stringInput = input.nextLine();  // Use nextLine() to allow spaces in input
        System.out.println("User input: " + stringInput);





        String rev = "";
        for (int i = 0; i < stringInput.length(); i++) {
            char ch = stringInput.charAt(i);
            rev = ch + rev;
        }

        System.out.println(rev);

    }
}
