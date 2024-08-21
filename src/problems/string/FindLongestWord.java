package problems.string;

import java.util.Scanner;

public class FindLongestWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to find the longest word in it: ");
        String s= scanner.nextLine().toLowerCase();

        System.out.println(longestWord(s));
    }

    public static String longestWord(String input) {
        String[] arr = input.split(" ");
        String longestString="";

        for (int i = 0; i < arr.length; i++) {
            if (longestString.length() < arr[i].length()) {
                 longestString= arr[i];
            }
        }
        return longestString;
    }
}
