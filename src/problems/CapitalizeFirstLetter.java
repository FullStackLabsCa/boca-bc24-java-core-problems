package problems;

import java.util.Scanner;

public class CapitalizeFirstLetter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string to capitalize: ");

        String str = scanner.nextLine();

        System.out.println("String after capitalizing first letter of each word: "+ capitalizeWords(str));
    }

    public static String capitalizeWords(String input){
        StringBuilder str = new StringBuilder();

        String[] strArr = input.split(" ");

        for(String st: strArr){
            str.append(st.substring(0, 1).toUpperCase()).append(st.substring(1)).append(" ");
        }

        return str.toString().trim();
    }
}
