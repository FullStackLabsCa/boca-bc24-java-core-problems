package problems;

import java.util.Arrays;

public class GenericMethodToFindOccurences {
    public static void main(String[] args) {
        Integer[] intArray = {3, 2, 3, 4};
        String[] stringArray = {"Hi", "Hello", "there"};
        Student s1 = new Student("Mohan", "Oakville", 3, "English");
        Student s2 = new Student("jay", "toronto", 2, "French");
        Student s3 = new Student("kumar", "Oshawa", 4, "Italian");

        Student[] students = {s1, s2, s3, s2};


        System.out.println(countOccurrences(stringArray, "there"));
        System.out.println(countOccurrences(intArray, 3));
        System.out.println(countOccurrences(students, s2));
    }

    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        for (T t : array) {
            if (t.equals(element)) {
                count++;
            }
        }
        return count;
    }
}