package io.reacticestax.genericsproblems;

public class CountOccurrences {
    //Write a generic method countOccurrences that counts the number of times a specific element appears in an array. The method should work with an array of any type.
    //
    //public static <T> int countOccurrences(T[] array, T element)

    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;

        for (T item : array) {

                if (item == null) {
                    if (element == null) {
                        count++;
                    }
                } else {
                    if (item.equals(element)) {
                        count++;
                    }
                }
            }
            return count;
        }

}




