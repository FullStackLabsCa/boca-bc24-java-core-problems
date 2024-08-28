package problems.string;

import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Collections.list;

public class Reverse {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to reverse it: ");
        String word = scanner.nextLine();

        int len= word.length();
        String rev= "";

        for (int i= len-1 ; i>=0 ; i--){
            rev = rev + word.charAt(i);
        }
        System.out.println(rev);
    }
}
