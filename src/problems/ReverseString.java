package problems;

import java.util.Scanner;

public class ReverseString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char[] arr = new char[input.length()];
        String output = "";
        int j = 0;

        for (int i = input.length() - 1; i >= 0; i--) {
            arr[j] = input.charAt(i);
            output += arr[j];
            j++;
        }
        System.out.println(output);
    }
}


