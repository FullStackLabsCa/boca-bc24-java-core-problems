package genricproblem;
import java.util.Scanner;

public class CountOccurrences {

    // Method to count occurrences of a specific element in an array

    public static <T> int countOccurrences(T[] array, T element) {

        if (array== null) {
            throw new IllegalArgumentException("Array cannot be zero");
        }
        int count = 0;

        for (T item : array) {
            if (item == null && element == null) {
                count++;
            } else if (item != null && item.equals(element)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String value = scanner.nextLine().trim().toLowerCase();

        // Convert the string to a character array

        Character[] charArray = new Character[value.length()];


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
