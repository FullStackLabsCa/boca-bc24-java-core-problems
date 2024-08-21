package stringproblem;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class FirstLetter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.println("Enter the value: ");
        String input = scanner.nextLine();
        String firstletter = capitalizeWords(input);
        out.println("The First Capital letter:  "+firstletter);
    }
    public static String capitalizeWords(String input){
        String[] parts = input.split(" ");
        String str ="";
        for(String part:parts){
            str =str+part.substring(0,1).toUpperCase()+part.substring(1).toLowerCase()+ " ";

            }

       return str.trim();
    }

}
