package problems.problems;

import java.util.Scanner;

public class ReverseString {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String reverse = reverseString(s);
        System.out.println(reverse);

    }

    public static String reverseString(String s) {
        int i = 0;
        int j = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);

        while (i < j) {
            char a = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, a);
            i++;
            j--;

        }
        return sb.toString();
    }
}



