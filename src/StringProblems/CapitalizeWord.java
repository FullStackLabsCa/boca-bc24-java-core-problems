package StringProblems;
import java.util.Scanner;
public class CaptalizeWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a String ");
        String input = scanner.nextLine();
        System.out.println(capitalizeWords(input));
    }
    public static String capitalizeWords(String input) {
        String[] values = input.split(" ");
        String firstWord = " ";
        for (String value : values) {
            String capitalWords = value.substring(0,1).toUpperCase() + value.substring(1).toLowerCase() + " ";
            firstWord +=capitalWords;
        }
        return firstWord;
    }
}
