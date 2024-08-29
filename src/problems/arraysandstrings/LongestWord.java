package problems.arraysandstrings;

import java.util.Scanner;

public class LongestWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string: ");

        String str = scanner.nextLine();

        System.out.println("Longest word is: " + findLongestWord(str));
    }

    public static String findLongestWord(String input) {
        String str = "";
        if (!input.isEmpty()) {
            String[] stringArr = input.split(" ");
            str = stringArr[0];

            for (String st : stringArr) {
                if (st.length() > str.length()) {
                    str = st;
                }
            }
        }
        return str;
    }
}
