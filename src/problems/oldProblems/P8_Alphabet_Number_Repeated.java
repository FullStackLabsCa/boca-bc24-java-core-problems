/*12 Read a given string and count how many times a given alphabet or number is repeated*/

package problems.oldProblems;

import java.util.Scanner;

public class P8_Alphabet_Number_Repeated {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.print("Enter the character or digit to count: ");
        char targetChar = scanner.next().charAt(0);

        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == targetChar) {
                count++;
            }
        }

        System.out.println("The character '" + targetChar + "' appears " + count + " times in the string.");

        scanner.close();

    }

}
