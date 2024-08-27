package problems.generic;
/*
Problem 2: Generic Method to Count Occurrences
        Objective:
        Write a generic method countOccurrences that counts the number of times a specific element appears in an array. The method should work with an array of any type.
public static <T> int countOccurrences(T[] array, T element)
 */

public class CountOccurenceInArray {

    public static <T> int countOccurrences(T[] array, T element) {
        int counter = 0;
        for (T arrayElement: array) {
            if(arrayElement.equals(element)) {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Integer[] arrayInteger = {2, 5,6,7, 8, 6, 2};
        String[] arrayString = {"apple", "banana", "mango", "cherry", "banana", "apple"};
        System.out.println("countOccurrences(arrayInteger) = " + countOccurrences(arrayInteger, 2));
        System.out.println("countOccurrences(arrayString) = " + countOccurrences(arrayString, "banana"));
    }
}
