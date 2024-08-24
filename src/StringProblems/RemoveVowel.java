package StringProblems;
import java.util.Scanner;

public class RemoveVowel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a String to remove the vowel");
        String input = scanner.nextLine();
        System.out.println(removeVowels(input));
    }
    public static String removeVowels(String input) {
        String resultString = "";
        for (int i = 0; i < input.length(); i++) {
            char value = input.charAt(i);
            if (value != 'a' && value != 'e' && value != 'i' && value != 'o' && value != 'u') {
                resultString += value;
            }
        }
        return resultString;
    }
}
