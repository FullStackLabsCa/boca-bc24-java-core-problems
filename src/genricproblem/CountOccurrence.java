package genricproblem;
import java.util.Scanner;

public class CountOccurrence {

    // Method to count occurrences of a specific element in an array

    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        for (T item : array) {
            if (item.equals(element)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read a string of values

        System.out.println("Enter a string:");
        String value = scanner.nextLine().trim().toLowerCase();

        // Convert the string to a character array

        Character[] charArray = new Character[value.length()];

        //iterate the value input

        for (int i = 0; i < value.length(); i++) {
            charArray[i] = value.charAt(i);
        }

        // Read the character to count

        System.out.println("Enter the specific you want  to count:");
        char charToCount = scanner.next().charAt(0);

        // Convert the char to Char for counting

        Character charObject = charToCount;

        // Count occurrences of the character

        int count = countOccurrences(charArray, charObject);

        // Output the result

        System.out.println("The word '" + charToCount + "' appears " + count + " times in the given output.");

        scanner.close();
    }
}
