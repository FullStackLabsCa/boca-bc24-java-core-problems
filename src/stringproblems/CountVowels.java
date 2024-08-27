package stringproblems;
import java.util.Scanner;
public class CountVowels {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter value to get the vowels");
        String input = scanner.nextLine();
        System.out.println("Vowels in String are " + countVowels(input));
    }
    public static int countVowels(String input) {
        int count = 0;
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            char value = input.charAt(i);
            if (value == 'a' || value == 'e' || value == 'i' || value == 'o' || value == 'u') {
                count++;
            }
        }
        return count;
    }
}
